
import './App.css'
import axios from "axios";
import {useState} from "react";

function App() {

    const[user, setUser] = useState<string>("")
    function login(){
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080': window.location.origin;

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    function getMe(){
        axios.get("/api/user/me")
            .then(response => {
                setUser(response.data)
            })
    }

  return (
    <>
     <h2>{user}</h2>
     <button onClick={login}>Login</button>
     <button onClick={getMe}>GetMe</button>
    </>
  )
}

export default App
