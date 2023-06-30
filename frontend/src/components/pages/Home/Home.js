import React, { useContext} from "react";
import Button from '@mui/material/Button'
import { YourContext } from "../Context/YourContext";


const Home = () => {

    const { url, setUrlValue } = useContext(YourContext);

    // setUrlValue('http://main.com') //incase you want to modify url

    const handleClick = () => {
        window.open(url)
    }


    return (
        <div>
            <Button variant="contained" color="primary">
                MULabel Button
            </Button><br/><br/>
            <Button variant="contained" color="secondary" onClick={handleClick}>
                MU Button
            </Button>
        </div>
    )
}

export default Home;