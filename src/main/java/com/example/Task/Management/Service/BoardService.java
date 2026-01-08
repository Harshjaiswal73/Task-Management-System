package com.example.Task.Management.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task.Management.Entity.Board;
import com.example.Task.Management.Entity.BoardCard;
import com.example.Task.Management.Entity.BoardColumn;
import com.example.Task.Management.Entity.Issue;
import com.example.Task.Management.Repository.BoardCardRepository;
import com.example.Task.Management.Repository.BoardColumnRepository;
import com.example.Task.Management.Repository.BoardRepository;
import com.example.Task.Management.Repository.IssueRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardColumnRepository boardColumnRepository;
	
	@Autowired
	private BoardCardRepository boardCardRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	
	public Board createBoard(Board bd) {
		return boardRepository.save(bd);
	}
	
	public Optional<Board>findById(Long id){
		return boardRepository.findById(id);
	}
	
	public List<BoardColumn>getByColumn(Long id){
		return boardColumnRepository.findByOrderPosition(id);
	}
	
	public List<BoardCard>getCardSForColumn(Long boardId,Long columnId){
		return boardCardRepository.findByBoardIdAndColumnIdOrderPosition(boardId, columnId);
	}
	
	@Transactional
	public BoardCard addIssueToBoard(Long boardId,Long columnId,Long issueId) {
		Issue issue = issueRepository.findById(issueId).orElseThrow(()-> new RuntimeException("issue not found"));
		
		boardCardRepository.findByIssueId(issueId).ifPresent(boardCardRepository::delete);
		
		BoardColumn column = boardColumnRepository.findById(columnId).orElseThrow(()-> new RuntimeException("column not found"));
		
		if(column.getWipLimit() !=null && column.getWipLimit()>0) {
			long count = boardCardRepository.countByBoardIdColumnId(boardId, columnId);
			if(count >=column.getWipLimit()) {
				throw new RuntimeException("Wip limit reached for column:" +column.getBoardName());
				
			}
		}
		List<BoardCard> existing = boardCardRepository.findByBoardIdAndColumnIdOrderPosition(boardId, columnId);
		int pos = existing.size();
		
		BoardCard card = new BoardCard();
		card.setBoardId(boardId);
		card.setColumn(column);
		card.setIssueId(issueId);
		card.setPosition(pos);
		
		card = boardCardRepository.save(card);
		
		if(column.getStatusKey()!=null) {
			issue.setIssueStatus(Enum.valueOf(com.example.Task.Management.Enum.IssueStatus.class,column.getStatusKey() ));
			
			issueRepository.save(issue);
		}
		
		return card;
	}
	
	@Transactional
	public void moveCard(Long boardId,Long cardId,Long columnId,int toPosition,String performedBy) {
		
		BoardCard card = boardCardRepository.findById(cardId).orElseThrow(()-> new RuntimeException("card not found"));
		
		BoardColumn from = card.getColumn();
		BoardColumn to = boardColumnRepository.findById(columnId).orElseThrow(()-> new RuntimeException("Column not found"));
		
		if(to.getWipLimit()!= null && to.getWipLimit()>0) {
			
			long count = boardCardRepository.countByBoardIdColumnId(boardId, columnId);
			if(!Objects.equals(from.getId(), to.getId() ) && count >= to.getWipLimit()) {
				throw new RuntimeException("Wip Limit exceeded"+ to.getBoardName());
			}
		}
		
		List<BoardCard> fromList = boardCardRepository.findByBoardIdAndColumnIdOrderPosition(boardId, from.getId());
		
		for(BoardCard bc : fromList) {
			
			if(bc.getPosition() > card.getPosition()) {
				bc.setPosition(bc.getPosition()-1);
				boardCardRepository.save(bc);
			}
		}
		
		List<BoardCard> toList = boardCardRepository.findByBoardIdAndColumnIdOrderPosition(boardId, to.getId());
		for(BoardCard bc :toList) {
			if(bc.getPosition() >= toPosition) {
				bc.setPosition(bc.getPosition()+1);
				boardCardRepository.save(bc);
			}
		}
		
		card.setColumn(to);
		card.setPosition(toPosition);
		boardCardRepository.save(card);
		
		
		Issue issue = issueRepository.findById(card.getIssueId()).orElseThrow(()-> new RuntimeException("Issue not found"));
		if(to.getStatusKey()!= null) {
			issue.setIssueStatus(Enum.valueOf(com.example.Task.Management.Enum.IssueStatus.class, to.getStatusKey()));
			issueRepository.save(issue);
		}
	}
	
	@Transactional
	public void recordColumn(Long boardId,Long columnId,List<Long> orderCardIds) {
		int pos = 0;
		for(Long cId : orderCardIds) {
			BoardCard bd = boardCardRepository.findById(cId).orElseThrow(()-> new RuntimeException("Card not found"));
			bd.setPosition(pos++);
			boardCardRepository.save(bd);
		}
		
	}
	
	@Transactional
	public void  startSprint(Long sprintId) {
		
	}
	
	@Transactional
	public void completeSprint(Long sprintId) {
		
	}
	
}
