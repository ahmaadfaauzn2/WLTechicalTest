// import logo from './logo.svg';
// import './App.css';
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PegawaiList from './components/PegawaiList/PegawaiList';
import PegawaiUpload from './components/PegawaiUpload/PegawaiUpload';
import PegawaiForm from './components/PegawaiForm/PegawaiForm';
import CheckKontrakPegawai from './components/CheckKontrak/CheckKontrakPegawai';
import MainMenu from './components/MainMenu/MainMenu';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainMenu />} />
        <Route path="/pegawai-list" element={<PegawaiList />} />
        <Route path="/pegawai-upload" element={<PegawaiUpload />} />
        <Route path="/pegawai-form" element={<PegawaiForm />} />
        <Route path="/check-kontrak" element={<CheckKontrakPegawai />} />
        </Routes>
    </Router>
  );
}

export default App;
