import { render, screen } from "@testing-library/react";
import React, {useContext} from "react";
import { YourContext, YourContextProvider } from "./YourContext";

describe("YourContext", () => {
  test("provides the correct context value", () => {
    const mockValue = {
      url: "http://test3.com",
      setUrlValue: jest.fn(),
    };

    render(
      <YourContextProvider value={mockValue}>
        <YourContext.Consumer>
          {(contextValue) => (
            <div data-testid="context-value">{contextValue.url}</div>
          )}
        </YourContext.Consumer>
      </YourContextProvider>
    );

    const contextValueElement = screen.getByTestId("context-value");
    expect(contextValueElement).toHaveTextContent("https://test.com");
  });
});

const TestComponent = () => {
  const contextValue = useContext(YourContext);
  return <div data-testid="context-value">{contextValue.url}</div>;
};

describe("YourContext", () => {
  test("Fixed: provides the correct mocked context value not actual value", () => {
    const mockValue = {
      url: "https://test3.com",
      setUrlValue: jest.fn(),
    };

    render(
      <YourContext.Provider value={mockValue}>
        <TestComponent />
      </YourContext.Provider>
    );

    const contextValueElement = screen.getByTestId("context-value");
    expect(contextValueElement).toHaveTextContent("https://test3.com");
  });
});
