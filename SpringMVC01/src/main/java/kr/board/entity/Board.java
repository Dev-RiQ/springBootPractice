package kr.board.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data //- Lombok API   @getter @setter @Value 이렇게 3개 어노테이션 합칩

public class Board {
  private int idx; //
  private String title; // 
  private String content; // 
  private String writer; // 
  private String indate; // 
  private int count; 
  // setter , getter

}
