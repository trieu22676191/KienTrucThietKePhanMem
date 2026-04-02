import React, { useState } from 'react';
// import './App.css';

function App() {
  const [count, setCount] = useState(0);

  return (
    <div className="app">
      <div className="container">
        <div className="card">
          <h1>React + Docker</h1>
          <p className="subtitle">
            Ứng dụng React chạy trong Docker Container
          </p>

          <div className="counter-section">
            <p className="counter-label">Bộ đếm:</p>
            <div className="counter-value">{count}</div>
            <div className="button-group">
              <button
                className="btn btn-decrement"
                onClick={() => setCount(count - 1)}
              >
                −
              </button>
              <button
                className="btn btn-reset"
                onClick={() => setCount(0)}
              >
                Reset
              </button>
              <button
                className="btn btn-increment"
                onClick={() => setCount(count + 1)}
              >
                +
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
