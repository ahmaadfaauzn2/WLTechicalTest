import React, { useEffect, useState } from "react";
import "./PegawaiList.css";

const PegawaiList = () => {
  const [pegawai, setPegawai] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [currentPegawai, setCurrentPegawai] = useState(null);
  const [editedData, setEditedData] = useState({});

  // Fetch data dari API
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/pegawai");
        const data = await response.json();
        setPegawai(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  // Fungsi membuka modal edit
  const handleEdit = (pegawaiData) => {
    setCurrentPegawai(pegawaiData);
    setEditedData({ ...pegawaiData }); // Isi form dengan data yang akan diedit
    setShowModal(true);
  };

  // Fungsi menyimpan data yang diedit
  const handleSaveEdit = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/pegawai/${currentPegawai.id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(editedData),
        }
      );
      if (response.ok) {
        setPegawai(
          pegawai.map((item) =>
            item.id === currentPegawai.id ? { ...item, ...editedData } : item
          )
        );
        setShowModal(false);
        alert("Data berhasil diperbarui!");
      } else {
        alert("Gagal memperbarui data. Silakan coba lagi.");
      }
    } catch (error) {
      console.error("Error updating data:", error);
      alert("Terjadi kesalahan saat memperbarui data.");
    }
  };
  

  // Fungsi untuk menghapus pegawai
  const handleDelete = async (id) => {
    const confirmDelete = window.confirm(
      "Apakah Anda yakin ingin menghapus data ini?"
    );
    if (confirmDelete) {
      try {
        const response = await fetch(`http://localhost:8080/api/pegawai/${id}`, {
          method: "DELETE",
        });
  
        if (response.ok) {
          setPegawai(pegawai.filter((item) => item.id !== id));
          alert("Data berhasil dihapus!");
        } else {
          alert("Gagal menghapus data. Silakan coba lagi.");
        }
      } catch (error) {
        console.error("Error deleting data:", error);
        alert("Terjadi kesalahan saat menghapus data.");
      }
    }
  };
  

  // Fungsi untuk menutup modal
  const closeModal = () => {
    setShowModal(false);
  };

  return (
    <div className="center-container">
      <div className="card-shadow">
        <div className="card-header-title bg-primary text-white">
          List Data Pegawai
        </div>
        <div className="card-body">
          <table className="table">
            <thead>
              <tr>
                <th>Nomor</th>
                <th>Nama</th>
                <th>Cabang</th>
                <th>Jabatan</th>
                <th>Tanggal Mulai Kontrak</th>
                <th>Tanggal Akhir Kontrak</th>
                <th>Aksi</th>
              </tr>
            </thead>
            <tbody>
              {pegawai.map((item, index) => (
                <tr key={item.id}>
                  <td>{index + 1}</td>
                  <td>{item.nama}</td>
                  <td>{item.cabang?.namaCabang || "Tidak tersedia"}</td>
                  <td>{item.jabatan?.namaJabatan || "Tidak tersedia"}</td>
                  <td>{item.tanggalMulaiKontrak}</td>
                  <td>{item.tanggalAkhirKontrak}</td>
                  <td>
                    <button
                      className="btn btn-warning btn-sm me-2"
                      onClick={() => handleEdit(item)}
                    >
                      Edit
                    </button>
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => handleDelete(item.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
              {pegawai.length === 0 && (
                <tr>
                  <td colSpan="7" className="text-center">
                    Tidak ada data pegawai
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>

      {/* Modal Box */}
      {showModal && (
        <div className="modal-overlay">
          <div className="modal-box">
            <div className="modal-header">
              <h3>Edit Data Pegawai</h3>
              <button className="close-button" onClick={closeModal}>
                &times;
              </button>
            </div>
            <div className="modal-body">
              <form>
                <div className="form-group">
                  <label>Nama</label>
                  <input
                    type="text"
                    className="form-control"
                    value={editedData.nama || ""}
                    disabled
                    onChange={(e) =>
                      setEditedData({ ...editedData, nama: e.target.value })
                    }
                  />
                </div>
                <div className="form-group">
                  <label>Tanggal Mulai Kontrak</label>
                  <input
                    type="date"
                    className="form-control"
                    value={editedData.tanggalMulaiKontrak || ""}
                    onChange={(e) =>
                      setEditedData({
                        ...editedData,
                        tanggalMulaiKontrak: e.target.value,
                      })
                    }
                  />
                </div>
                <div className="form-group">
                  <label>Tanggal Akhir Kontrak</label>
                  <input
                    type="date"
                    className="form-control"
                    value={editedData.tanggalAkhirKontrak || ""}
                    onChange={(e) =>
                      setEditedData({
                        ...editedData,
                        tanggalAkhirKontrak: e.target.value,
                      })
                    }
                  />
                </div>
              </form>
            </div>
            <div className="modal-footer">
              <button className="btn btn-secondary" onClick={closeModal}>
                Batal
              </button>
              <button className="btn btn-primary" onClick={handleSaveEdit}>
                Simpan
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default PegawaiList;
