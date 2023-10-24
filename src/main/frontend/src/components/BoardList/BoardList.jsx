import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import './Board.css';

const itemsPerPage = 10;

function BoardList(props) {
    const [currentPage, setCurrentPage] = useState(0);
    const [displayedData, setDisplayedData] = useState([]);
    const pageCount = Math.ceil(props.data.length / itemsPerPage);

    useEffect(() => {
        const startIndex = currentPage * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;

        // 역순으로 게시물 순서를 뒤집습니다.
        const reversedData = [...props.data].reverse();
        const slicedData = reversedData.slice(startIndex, endIndex);

        setDisplayedData(slicedData);
    }, [currentPage, props.data]);

    const handlePageClick = (selectedPage) => {
        setCurrentPage(selectedPage.selected);
    };

    return (
        <>
            <h3>자유게시판</h3>
            {displayedData.length >= 0 ? (
                <table>
                    <thead>
                    <tr>
                        <th>순서</th>
                        <th>제목</th>
                        <th>ID</th>
                        <th>조회수</th>
                    </tr>
                    </thead>
                    <tbody>
                    {displayedData.map((board, index) => (
                        <tr key={index}>
                            <td>{props.data.length - (currentPage * itemsPerPage) - index}</td>
                            <td>
                                <Link to="/detail" state={{ id: board.id }}>
                                    {board.title}
                                </Link>
                            </td>
                            <td>{board.id}</td>
                            <td>{(board.hit)/2}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            ) : (
                <p>게시글이 존재하지 않습니다</p>
            )}

            <div className="pagination-container">
                <ReactPaginate
                    previousLabel={'이전'}
                    nextLabel={'다음'}
                    breakLabel={'...'}
                    pageCount={pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}
                    onPageChange={handlePageClick}
                    containerClassName={'pagination'}
                    subContainerClassName={'pages pagination'}
                    activeClassName={'active'}
                />
            </div>
        </>
    );
}

export default BoardList;