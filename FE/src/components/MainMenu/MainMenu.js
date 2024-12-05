import React from "react";
import { Card, Col, Row, Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom"; // Import hook useNavigate
import "./MainMenu.css";

const MainMenu = () => {
  const navigate = useNavigate(); // Gunakan useNavigate untuk navigasi

  return (
    <div className="center-container">
      <Container>
        {/* Baris Pertama: Card 1 dan Card 2 */}
        <Row className="g-4 justify-content-center">
          <Col md={6}>
            <Card className="card-shadow card-design">
              <Card.Body className="text-center">
                <h5 className="card-title">List Data Pegawai</h5>
                <p className="card-text">
                  List semua data pegawai edit dan delete
                </p>
                <button
                  className="btn btn-primary"
                  onClick={() => navigate("/pegawai-list")}
                >
                  Lihat Data
                </button>
              </Card.Body>
            </Card>
          </Col>

          <Col md={6}>
            <Card className="card-shadow card-design card-2">
              <Card.Body className="text-center">
                <h5 className="card-title">Cek Kontrak Pegawai</h5>
                <p className="card-text">
                  Cek status kontrak pegawai yang hampir habis.
                </p>
                <button
                  className="btn btn-success"
                  onClick={() => navigate("/check-kontrak")}
                >
                  Cek Kontrak
                </button>
              </Card.Body>
            </Card>
          </Col>
        </Row>

        {/* Baris Kedua: Card 3 dan Card 4 */}
        <Row className="g-4 justify-content-center card-3">
          <Col md={6}>
            <Card className="card-shadow card-design">
              <Card.Body className="text-center">
                <h5 className="card-title">Input Data Pegawai</h5>
                <p className="card-text">
                  Input Data Pegawai baru ke dalam sistem.
                </p>
                <button
                  className="btn btn-info"
                  onClick={() => navigate("/pegawai-form")}
                >
                  Input Data
                </button>
              </Card.Body>
            </Card>
          </Col>

          <Col md={6}>
            <Card className="card-shadow card-design card-4">
              <Card.Body className="text-center">
                <h5 className="card-title">Upload Data Pegawai</h5>
                <p className="card-text">
                  Unggah file Excel berisi data pegawai untuk diproses.
                </p>
                <button
                  className="btn btn-warning"
                  onClick={() => navigate("/pegawai-upload")}
                >
                  Unggah Data
                </button>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default MainMenu;
