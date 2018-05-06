package authorize.jaeho.yoon.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import authorize.jaeho.yoon.board.dao.BoardDAO;
import authorize.jaeho.yoon.board.vo.Board;
import authorize.jaeho.yoon.common.util.MicrosoftWordDocReader;
import authorize.jaeho.yoon.common.util.PageNavigator;

@Controller
public class BoardController {
	@Autowired
	BoardDAO dao;
	
	final int countPerPage = 5;
	final int pagePerGroup =5;
	
	
	@ResponseBody
	@RequestMapping(value ="content", method = RequestMethod.POST)
	public String[] content(String content){
		/*String result = null;
		try {
			result = URLEncoder.encode( MicrosoftWordDocReader.convertDocFile() , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		String[] paragraphs = MicrosoftWordDocReader.readDocFile(content);
		return paragraphs;
	}
	
	
	@ResponseBody
	@RequestMapping(value ="select", method = RequestMethod.GET)
	public HashMap<String,Object> selectPage(String signState_id, String isPersonal, String person,
			@RequestParam(value="searchText",defaultValue="")String searchText,
			@RequestParam(value="page",defaultValue="1")int page,
			@RequestParam(value="searchSelect",defaultValue="doc_content")String searchSelect){
		HashMap<String,Object> map = new HashMap<String,Object>();
		HashMap<String,Object> model = new HashMap<String,Object>();
		map.put("searchText", searchText);
		map.put("searchSelect", searchSelect);
		map.put("signState_ID",signState_id);
		map.put("isPersonal", isPersonal);
		map.put("person",person);
		int total = dao.getTotal(map);
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
		ArrayList<Board> list = dao.selectPage(map,navi.getStartRecord(), navi.getCountPerPage());
		model.put("searchText", searchText);
		model.put("searchSelect", searchSelect);
		model.put("navi", navi);
		model.put("list", list);
		model.put("signState_ID",signState_id);
		model.put("isPersonal", isPersonal);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value ="need", method = RequestMethod.GET)
	public HashMap<String,Object> selectAll(String employee_id,
			@RequestParam(value="page",defaultValue="1")int page,
			@RequestParam(value="searchSelect",defaultValue="doc_content")String searchSelect){
		HashMap<String,Object> map = new HashMap<String,Object>();
		HashMap<String,Object> model = new HashMap<String,Object>();
		map.put("employee_id", employee_id);
		int total = dao.getTotal2(map);
		PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);
		ArrayList<Board> list = dao.selectAll(map,navi.getStartRecord(), navi.getCountPerPage());
		model.put("navi", navi);
		model.put("list", list);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "selectOne", method = RequestMethod.POST)
	public HashMap<String,Object> selectOne(int doc_id,
			@RequestParam(value="searchText",defaultValue="")String searchText,
			@RequestParam(value="page",defaultValue="1")int page){
		Board board = dao.selectOne(doc_id);
		HashMap<String,Object> model = new HashMap<String,Object>();
		model.put("page", page);
		model.put("board", board);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(int doc_id){
		Board board = dao.selectOne(doc_id);
		dao.delete(doc_id);
		File delFile = new File("C:/"+board.getDoc_content());
		if (delFile.isFile()) {
			delFile.delete();
		}
		
	/*	File file = new File(board.getDoc_content());
		file.delete();*/
		
	}
	@ResponseBody
	@RequestMapping(value = "depart", method = RequestMethod.GET)
	public ArrayList<Board> readDepart(){
		ArrayList<Board> list = dao.readDepart();
		return list;
	}
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(@RequestBody Board board){
		dao.update(board);
	}
	
	@ResponseBody
	@RequestMapping(value = "writeform", method = RequestMethod.GET)
	public HashMap<String,Object> writeform(){
		HashMap<String,Object> map = new HashMap<String,Object>();
		ArrayList<Board> list = dao.readDepart();
		ArrayList<String> content = dao.readContent();
		map.put("depart", list);
		map.put("content", content);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public void write(@RequestBody Board board){
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyymmddhhmmss");
		String fileName = dayTime.format(new Date(time));
		board.setDoc_content(fileName+".doc");
		try {
			MicrosoftWordDocReader.newWordDoc(fileName, board.getContent_real());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.write(board);
	}
	
	@ResponseBody
	@RequestMapping(value = "sign", method = RequestMethod.POST)
	public void sign(@RequestBody Board board){
		String manager_id = dao.getManager(board.getEmployee_id());
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("manager_id", manager_id);
		map.put("board", board);
		dao.sign(map);
	}
	@ResponseBody
	@RequestMapping(value = "reject", method = RequestMethod.POST)
	public void reject(int doc_id){
		System.out.println(doc_id);
		dao.reject(doc_id);
	}
	
	@RequestMapping(value="download", method = RequestMethod.GET)
	public void FileDownlaod(int doc_id, HttpServletResponse response){
		Board board =dao.selectOne(doc_id);
		//다운로드 규칙//
		//1.원래 파일명을 response Header에 인코딩해서 등록을 해주어야함
		String originalFile = board.getDoc_content();
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
						+URLEncoder.encode(originalFile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//2.outputstream을 연결 시켜 주어야 함
		//저장된 파일 경로
		String fullpath = "/"+board.getDoc_content();
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream fis = null;
		ServletOutputStream sos = null;
		try {
			fis = new FileInputStream(fullpath);
			sos = response.getOutputStream();
			
			FileCopyUtils.copy(fis, sos);
			
			fis.close();
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
