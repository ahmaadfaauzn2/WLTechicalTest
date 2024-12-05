import React, { useState, useEffect } from "react";
import { Table, Container, Form,  Card } from "react-bootstrap";
import axios from "axios";
import "./CheckKontrakPegawai.css"; // Menambahkan styling untuk tabel dan card

const CheckKontrakPegawai = () => {
  const [pegawaiList, setPegawaiList] = useState([]); // Daftar pegawai yang terdaftar
  const [pegawaiId, setPegawaiId] = useState(""); // ID pegawai yang dipilih
  const [pegawaiData, setPegawaiData] = useState(null); // Data kontrak pegawai
  const [loading, setLoading] = useState(false); // Status loading
  const [error, setError] = useState(""); // Status error

  // Mengambil daftar pegawai dari backend
  useEffect(() => {
    const fetchPegawaiList = async () => {
      try {
        setLoading(true);
        const response = await axios.get("http://localhost:8080/api/pegawai");
        if (response.data) {
          setPegawaiList(response.data);  // Menyimpan data pegawai
        } else {
          setError("Data pegawai tidak ditemukan.");
        }
      } catch (error) {
        setError("Terjadi kesalahan dalam mengambil data pegawai.");
      } finally {
        setLoading(false);
      }
    };

    fetchPegawaiList(); // Memanggil fetchPegawaiList untuk mengambil data pegawai saat komponen pertama kali dimuat
  }, []); // Dependensi kosong, hanya dijalankan sekali ketika komponen dimuat

  // Fungsi untuk mengambil data kontrak pegawai berdasarkan ID
  const fetchKontrakPegawai = async (id) => {
    try {
      setLoading(true);
      setError("");
      const response = await axios.get(`http://localhost:8080/api/pegawai/kontrak-habis/${id}`);
      
      // Validasi apakah data ada
      if (response.data) {
        setPegawaiData(response.data);
      } else {
        setError("Data tidak ditemukan untuk pegawai tersebut.");
        setPegawaiData(null);
      }
      setLoading(false);
    } catch (error) {
      setError("Terjadi kesalahan dalam mengambil data kontrak.");
      setPegawaiData(null);
      setLoading(false);
    }
  };

  // Handle perubahan pilihan pegawai di dropdown
  const handleSelectChange = (event) => {
    const selectedId = event.target.value;
    setPegawaiId(selectedId); // Menyimpan ID pegawai yang dipilih
    if (selectedId) {
      fetchKontrakPegawai(selectedId); // Panggil API untuk mendapatkan data kontrak pegawai
    } else {
      setPegawaiData(null); // Jika tidak ada ID, set data kontrak menjadi null
    }
  };

  return (
    <div className="center-container">
      <Container className="mt-4">
        <Card className="card-shadow">
          <Card.Header className="bg-primary text-white card-header-title">
            Cek Kontrak Pegawai
          </Card.Header>
          <Card.Body>
            {/* Dropdown untuk memilih pegawai */}
            <Form.Group controlId="pegawaiSelect" className="mb-3">
  <Form.Label>Pilih Pegawai</Form.Label>
  <Form.Control as="select" value={pegawaiId} onChange={handleSelectChange}>
    <option value="">-- Pilih Pegawai --</option>
    {pegawaiList.length > 0 ? (
      pegawaiList.map((pegawai) => (
        <option key={pegawai.id} value={pegawai.id}>
          {pegawai.nama}
        </option>
      ))
    ) : (
      <option disabled>No pegawai available</option>
    )}
  </Form.Control>
</Form.Group>

            {/* Menampilkan pesan loading atau error */}
            {loading && <div>Loading...</div>}
            {error && <div className="error-message">{error}</div>}

            {/* Menampilkan tabel pegawai jika data kontrak ditemukan */}
            {pegawaiData && (
              <Table striped bordered hover className="mt-4">
                <thead className="table-dark">
                  <tr>
                    <th>Nomor</th>
                    <th>Nama</th>
                    <th>Cabang</th>
                    <th>Jabatan</th>
                    <th>Tanggal Mulai Kontrak</th>
                    <th>Tanggal Akhir Kontrak</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <td>{pegawaiData.nama}</td>
                    <td>{pegawaiData.namaCabang}</td>
                    <td>{pegawaiData.namaJabatan}</td>
                    <td>{pegawaiData.tanggalMulaiKontrak}</td>
                    <td>{pegawaiData.tanggalAkhirKontrak}</td>
                  </tr>
                </tbody>
              </Table>
            )}

            {/* Menampilkan pesan jika tidak ada data kontrak */}
            {pegawaiData === null && pegawaiId && !loading && (
              <div className="mt-3">
                Tidak ada data kontrak untuk pegawai dengan ID {pegawaiId}.
              </div>
            )}
          </Card.Body>  
        </Card>
      </Container> 
    </div>
  );
};

export default CheckKontrakPegawai;
