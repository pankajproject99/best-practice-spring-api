import React, { createContext, useState } from "react";

export const YourContext = createContext();

export const YourContextProvider = ({children}) => {
    const [url, setUrl] = useState('https://test.com');

    const setUrlValue = (value) => {
        setUrl(value);
    };

    const contextValue = {
        url,
        setUrlValue
    }

    return (
        <YourContext.Provider value={contextValue}>
            {children}
        </YourContext.Provider>
    )
}