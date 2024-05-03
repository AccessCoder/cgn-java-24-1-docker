
import './App.css'
import axios from "axios";
import {useEffect, useState} from "react";
import {Route, Routes} from "react-router-dom";
import HelloPage from "./HelloPage.tsx";
import CiaoPage from "./CiaoPage.tsx";
import ProtectedRoute from "./ProtectedRoute.tsx";

function App() {

    const[user, setUser] = useState<string>("DEFAULT")
    function login(){
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080': window.location.origin;

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    useEffect(() =>
           getMe()
    , [user])

    function logout(){
        axios.get("/api/user/logout")
            .then(() => getMe())
    }
    function getMe(){
        axios.get("/api/user/me2")
            .then(response => {
                setUser(response.data)
            })
    }



  return (
    <>
     <h2>{user}</h2>
     <button onClick={login}>Login</button>
     <button onClick={getMe}>GetMe</button>
     <button onClick={logout}>Logout</button>
        <Routes>
            <Route element={<HelloPage/>} path={"/"}/>

            <Route element={<ProtectedRoute userName={user}/>}>
            <Route element={<CiaoPage/>} path={"/ciao"}/>
            </Route>
        </Routes>
    </>
  )
}

export default App
