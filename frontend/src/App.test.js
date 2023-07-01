import { render, screen } from "@testing-library/react";
import App from "./App";

describe("App", () => {
  test("Fixed: renders the App component without error", () => {
    // Define a wrapper function to render the App component
    const renderApp = () => render(<App />);
    
    // Assert that no error is thrown during rendering
    expect(renderApp).not.toThrow();
  });
});
