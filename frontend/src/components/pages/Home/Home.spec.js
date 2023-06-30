import React from "react";
import { fireEvent, render, screen } from "@testing-library/react";
import Home from "./Home";
import { YourContext } from "../Context/YourContext";

describe("Test", () => {
  test("test", () => {
    render(
      <YourContext.Provider value={{}}>
        <Home />
      </YourContext.Provider>
    );
    expect(true);
  });
});

describe("Home Page Test", () => {
  test("renders UI Button", () => {
    render(
      <YourContext.Provider value={{}}>
        <Home />
      </YourContext.Provider>
    );
    //catch button
    const muBUtton = screen.getByRole("button", { name: "MULabel Button" });
    expect(muBUtton).toBeInTheDocument();
  });

  test("launch url in another window", () => {
    const originalOpen = window.open;
    window.open = jest.fn();

    render(
      <YourContext.Provider value={{url: "https://test1.com"}}>
        <Home />
      </YourContext.Provider>
    );

    //catch button
    const launchButton = screen.getByRole("button", { name: "MU Button" });
    fireEvent.click(launchButton);

    expect(window.open).toHaveBeenCalledWith("https://test1.com");
  });

  test("Mock context also", () => {
    const mockUrl = "https://test2.com";
    const mockSetUrl = jest.fn();

    const contextValue = {
      url: mockUrl,
      setUrlValue: mockSetUrl,
    };

    const openMock = jest.spyOn(window, "open").mockImplementation(() => {});

    const { getByText } = render(
      <YourContext.Provider value={contextValue}>
        <Home />
      </YourContext.Provider>
    );

    const launchButton = getByText("MU Button");
    fireEvent.click(launchButton);

    expect(openMock).toHaveBeenCalledWith(mockUrl);
    expect(mockSetUrl).not.toHaveBeenCalled();

    openMock.mockRestore();
  });
});
