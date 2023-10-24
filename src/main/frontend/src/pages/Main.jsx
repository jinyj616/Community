// # main/frontend/src/pages/Main.jsx

import { React, useEffect, useState } from "react";
import BoardList from "../components/BoardList/BoardList";
import axios from 'axios';
import { Link } from 'react-router-dom';
import './Main.css';


const Main = () => {
    const [data, setData] = useState("")

    useEffect(() => {
        const getBoardList = async () => {
            console.log('Main/getBoardList()');
            let response = await axios.get("/api/board-list");
            console.log('main/response: ', response);
            setData(response.data.data);
        };
        getBoardList();
    }, [])


    return (
        <>
            <header>
                <nav>
                    <ul>
                        <li><a href="#">Match</a></li>
                        <li><a href="#">목록</a></li>
                        <li><a href="#">선수&팀</a></li>
                        <li><a href="#">뉴스</a></li>
                        <li><a href="#">커뮤니티</a></li>
                    </ul>
                </nav>
            </header>

            <div className="banner">
                <a> <img src = "/img/banner.PNG" />  </a>
            </div>

            <div className="main">
                <div className="left-sidebar">
                    <h3>Left Sidebar</h3>
                    <ul>
                        <li><a href="#">Left Menu Item 1</a></li>
                        <li><a href="#">Left Menu Item 2</a></li>
                        <li><a href="#">Left Menu Item 3</a></li>
                        <li><a href="#">Left Menu Item 4</a></li>
                        <li><a href="#">Left Menu Item 5</a></li>
                        {/* Add more menu items as needed */}
                    </ul>
                </div>

                <div className="container1">
                    <Link to={"/create-board"} >
                        <a> <img src = "/img/write.PNG" />  </a>
                    </Link>
                    <BoardList data={data}/>
                </div>

                <div className="right-sidebar">
                    <h3>Right Sidebar</h3>
                    <ul>
                        <li><a href="#">Right Menu Item 1</a></li>
                        <li><a href="#">Right Menu Item 2</a></li>
                        <li><a href="#">Right Menu Item 3</a></li>
                        {/* Add more menu items as needed */}
                    </ul>
                </div>
            </div>

            <footer className="footer">
                <p>&copy; 2023 Your Website</p>
                <p>Contact: contact@example.com</p>
            </footer>

        </>
    );
};
export default Main;