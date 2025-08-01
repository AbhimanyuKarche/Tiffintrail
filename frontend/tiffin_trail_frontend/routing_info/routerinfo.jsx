import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "../Components/Home";
import About from "../routes/About";
import Join from "../routes/Join";
import Testing from "../routes/Testing";
import TestingSeller from "../routes/TestingSeller";

const routerinfo = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/join" element={<Join />} />
        <Route path="/testing" element={<Testing />} />
        <Route path="/testingSeller" element={<TestingSeller />} />
      </Routes>
    </BrowserRouter>
  );
};

export default routerinfo;
