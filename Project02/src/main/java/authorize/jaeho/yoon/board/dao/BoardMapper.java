package authorize.jaeho.yoon.board.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import authorize.jaeho.yoon.board.vo.Board;

public interface BoardMapper {
	public ArrayList<Board> selectPage(HashMap<String,Object> map, RowBounds rb);
	public int getTotal(HashMap<String,Object> map);
	public int getTotal2(HashMap<String,Object> map);
	public Board selectOne(int doc_id);
	public ArrayList<Board> selectAll(HashMap<String,Object> map, RowBounds rb);
	public void delete(int doc_id);
	public ArrayList<Board> readDepart();
	public void update(Board board);
	public ArrayList<String> readContent();
	public void write(Board board);
	public void sign(HashMap<String,Object> map);
	public String getManager(String employee_id);
	public void reject(int doc_id);
}
