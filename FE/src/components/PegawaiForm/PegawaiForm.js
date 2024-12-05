import React, { useState } from "react";
import { Card, Form, Button, Row, Col } from "react-bootstrap";
import './PegawaiForm.css'; // Import the CSS file

const PegawaiForm = () => {
  const [formData, setFormData] = useState({
    nama: "",
    namaCabang: "",
    namaJabatan: "",
    tanggalMulaiKontrak: "",
    tanggalAkhirKontrak: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); // Mencegah refresh halaman
    
    // Menyiapkan data yang akan dikirim ke backend
    const requestData = {
      nama: formData.nama,
      namaCabang: parseInt(formData.namaCabang), // Pastikan ID cabang dikirim
      namaJabatan: parseInt(formData.namaJabatan), // Pastikan ID jabatan dikirim
      tanggalMulaiKontrak: formData.tanggalMulaiKontrak,
      tanggalAkhirKontrak: formData.tanggalAkhirKontrak,
    };

    try {
      const response = await fetch("http://localhost:8080/api/pegawai", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData), // Mengirim data dalam format JSON
      });

      if (response.ok) {
        alert("Pegawai berhasil disimpan!");
        // Reset form setelah pengiriman data berhasil
        setFormData({
          nama: "",
          namaCabang: "",
          namaJabatan: "",
          tanggalMulaiKontrak: "",
          tanggalAkhirKontrak: "",
        });
      } else {
        alert("Gagal menyimpan pegawai. Silakan coba lagi.");
      }
    } catch (error) {
      console.error("Error saving data:", error);
      alert("Terjadi kesalahan saat menyimpan data.");
    }
  };

  return (
    <div className="center-container">
      <Card className="card-shadow">
        <Card.Header className="bg-primary text-white card-header-title">
          Tambah Pegawai
        </Card.Header>
        <Card.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group as={Row} className="mb-3" controlId="formNama">
              <Form.Label column sm={4} className="form-nama">
                Nama:
              </Form.Label>
              <Col sm={8}>
                <Form.Control
                  type="text"
                  name="nama"
                  value={formData.nama}
                  onChange={handleChange}
                  placeholder="Masukkan nama"
                />
              </Col>
            </Form.Group>

            <Form.Group as={Row} className="mb-3 form-group" controlId="formNamaCabang">
              <Form.Label column sm={4} className="form-label">
                Nama Cabang:
              </Form.Label>
              <Col sm={8}>
                <Form.Select
                  name="namaCabang"
                  value={formData.namaCabang}
                  onChange={handleChange}
                  aria-label="Pilih Nama Cabang"
                >
                  <option value="">Pilih Nama Cabang</option>
                  <option value={1}>Jakarta</option>
                  <option value={2}>Bandung</option>
                  <option value={3}>Surabaya</option>
                </Form.Select>
              </Col>
            </Form.Group>

            <Form.Group as={Row} className="mb-3" controlId="formNamaJabatan">
              <Form.Label column sm={4}>
                Nama Jabatan:
              </Form.Label>
              <Col sm={8}>
                <Form.Select
                  name="namaJabatan"
                  value={formData.namaJabatan}
                  onChange={handleChange}
                  aria-label="Pilih Nama Jabatan"
                >
                  <option value="">Pilih Nama Jabatan</option>
                  <option value={1}>Manager</option>
                  <option value={2}>Staff Administrasi</option>
                  <option value={3}>Engineer</option>
                  <option value={4}>HR Officer</option>
                  <option value={5}>IT Support</option>
                </Form.Select>
              </Col>
            </Form.Group>

            <Form.Group as={Row} className="mb-3" controlId="formTanggalMulaiKontrak">
              <Form.Label column sm={4}>
                Tanggal Mulai Kontrak:
              </Form.Label>
              <Col sm={8}>
                <Form.Control
                  type="date"
                  name="tanggalMulaiKontrak"
                  value={formData.tanggalMulaiKontrak}
                  onChange={handleChange}
                />
              </Col>
            </Form.Group>

            <Form.Group as={Row} className="mb-3" controlId="formTanggalAkhirKontrak">
              <Form.Label column sm={4}>
                Tanggal Akhir Kontrak:
              </Form.Label>
              <Col sm={8}>
                <Form.Control
                  type="date"
                  name="tanggalAkhirKontrak"
                  value={formData.tanggalAkhirKontrak}
                  onChange={handleChange}
                />
              </Col>
            </Form.Group>

            <Button type="submit" variant="primary" className="w-100">
              Simpan
            </Button>
          </Form>
        </Card.Body>
      </Card>
    </div>
  );
};

export default PegawaiForm;
