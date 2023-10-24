package com.example.comunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.comunity.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}