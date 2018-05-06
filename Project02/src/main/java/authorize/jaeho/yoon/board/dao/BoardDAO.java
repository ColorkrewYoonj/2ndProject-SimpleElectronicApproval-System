package authorize.jaeho.yoon.board.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import authorize.jaeho.yoon.board.vo.Board;


@Repository
public class BoardDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<Board> selectPage(HashMap<String,Object> map, int startPageGroup, int countPerPage){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		ArrayList<Board> list = null;
		RowBounds rb = new RowBounds(startPageGroup,countPerPage);
		try {
			list = mapper.selectPage(map,rb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Board> selectAll(HashMap<String,Object> map, int startPageGroup, int countPerPage){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		ArrayList<Board> list = null;
		RowBounds rb = new RowBounds(startPageGroup,countPerPage);
		try {
			list = mapper.selectAll(map,rb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int getTotal(HashMap<String,Object> map){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int total = 0;
		try {
			total = mapper.getTotal(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public int getTotal2(HashMap<String,Object> map){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int total = 0;
		try {
			total = mapper.getTotal2(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public Board selectOne(int doc_id){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		Board board = null;
		try {
			board = mapper.selectOne(doc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	public void delete(int doc_id){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			mapper.delete(doc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Board> readDepart(){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		ArrayList<Board> list = null;
		try {
			list = mapper.readDepart();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void update(Board board){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			mapper.update(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> readContent(){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		ArrayList<String> list = null;
		try {
			list = mapper.readContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void write(Board board){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			mapper.write(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sign(HashMap<String,Object> map){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			mapper.sign(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getManager(String employee_id){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		String manager_id = null;
		try {
			manager_id = mapper.getManager(employee_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return manager_id;
	}
	public void reject(int doc_id){
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		try {
			mapper.reject(doc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
