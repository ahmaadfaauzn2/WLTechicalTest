import React, { useState } from "react";
import { Button, Form, Card, Col, Row } from "react-bootstrap";
import * as XLSX from "xlsx"; // Import xlsx untuk membaca file Excel
import axios from "axios"; // Import axios untuk mengirim data ke backend
import './PegawaiUpload.css'; // Tambahkan CSS jika diperlukan

const PegawaiUpload = () => {
  const [file, setFile] = useState(null); // State untuk file yang dipilih
  const [status, setStatus] = useState(""); // Status untuk notifikasi
  const [loading, setLoading] = useState(false); // Status loading

  // Fungsi untuk menangani perubahan pada file input
  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    setStatus(""); // Reset status saat file diubah
  };

  // Fungsi untuk membaca dan mengunggah file Excel
  const handleFileUpload = async (e) => {
    e.preventDefault();
  
    if (!file) {
      setStatus("Pilih file terlebih dahulu!");
      return;
    }
  
    // Membuat objek FormData untuk mengirimkan file
    const formData = new FormData();
    formData.append("file", file);
  
    try {
      setLoading(true);
  
      // Mengirimkan FormData ke backend menggunakan POST request
      const response = await axios.post("http://localhost:8080/api/pegawai/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data", // Pastikan Content-Type adalah multipart
        },
      });
  
      if (response.status === 200) {
        setStatus("Data pegawai berhasil diunggah!");
      } else {
        setStatus("Terjadi kesalahan saat mengunggah data.");
      }
    } catch (error) {
      console.error(error);
      setStatus("Terjadi kesalahan dalam memproses file.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="upload-form-container">
      <div className="center-container">
      <Card className="card-shadow">
        <Card.Header className="bg-primary text-white card-header-title">
          Form Upload Data Pegawai
        </Card.Header>
        <Card.Body>
          <Form onSubmit={handleFileUpload}>
            <Row className="mb-3">
              <Form.Label column sm={4}>
                Pilih File Excel:
              </Form.Label>
              <Col sm={8}>
                <Form.Control
                  type="file"
                  accept=".xlsx, .xls"
                  onChange={handleFileChange}
                />
              </Col>
            </Row>

            <Button
              type="submit"
              variant="primary"
              className="w-100"
              disabled={loading}
            >
              {loading ? "Mengunggah..." : "Unggah Data"}
            </Button>
          </Form>

          {/* Menampilkan status notifikasi */}
          {status && (
            <div className="mt-3">
              <p>{status}</p>
            </div>
          )}
        </Card.Body>
      </Card>
      </div>
    </div>
  );
};

export default PegawaiUpload;
