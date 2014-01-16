# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.27
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2014-01-16 19:38:30
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for medrec
CREATE DATABASE IF NOT EXISTS `medrec` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `medrec`;


# Dumping structure for table medrec.dokter
CREATE TABLE IF NOT EXISTS `dokter` (
  `no_dokter` varchar(7) NOT NULL DEFAULT '',
  `nm_dokter` varchar(45) NOT NULL,
  `id_spesialis` varchar(10) NOT NULL DEFAULT '',
  `tgl_kerja_dok` date DEFAULT '0000-00-00',
  `alamat_dok` text,
  PRIMARY KEY (`no_dokter`),
  KEY `fk_spesialis` (`id_spesialis`),
  CONSTRAINT `fk_spesialis` FOREIGN KEY (`id_spesialis`) REFERENCES `spesialis` (`id_spesialis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.dokter: ~0 rows (approximately)
/*!40000 ALTER TABLE `dokter` DISABLE KEYS */;
/*!40000 ALTER TABLE `dokter` ENABLE KEYS */;


# Dumping structure for table medrec.jaminan
CREATE TABLE IF NOT EXISTS `jaminan` (
  `id_jaminan` varchar(20) NOT NULL DEFAULT '',
  `nm_jaminan` varchar(45) NOT NULL DEFAULT '',
  `ket_jaminan` text,
  PRIMARY KEY (`id_jaminan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.jaminan: ~0 rows (approximately)
/*!40000 ALTER TABLE `jaminan` DISABLE KEYS */;
/*!40000 ALTER TABLE `jaminan` ENABLE KEYS */;


# Dumping structure for table medrec.obat
CREATE TABLE IF NOT EXISTS `obat` (
  `id_obat` varchar(45) NOT NULL,
  `ket_obat` text,
  PRIMARY KEY (`id_obat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.obat: ~0 rows (approximately)
/*!40000 ALTER TABLE `obat` DISABLE KEYS */;
/*!40000 ALTER TABLE `obat` ENABLE KEYS */;


# Dumping structure for table medrec.pasien
CREATE TABLE IF NOT EXISTS `pasien` (
  `no_rm` varchar(6) NOT NULL DEFAULT '',
  `nm_pas` varchar(45) NOT NULL,
  `jk_pas` enum('L','P') NOT NULL,
  `tgl_lahir` date NOT NULL,
  `agama` varchar(10) NOT NULL DEFAULT '',
  `alamat_pas` text,
  PRIMARY KEY (`no_rm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.pasien: ~0 rows (approximately)
/*!40000 ALTER TABLE `pasien` DISABLE KEYS */;
/*!40000 ALTER TABLE `pasien` ENABLE KEYS */;


# Dumping structure for table medrec.pelayanan_tindakan
CREATE TABLE IF NOT EXISTS `pelayanan_tindakan` (
  `no_daftar` varchar(12) NOT NULL DEFAULT '',
  `no_tindakan` varchar(9) NOT NULL DEFAULT '' COMMENT 'TIND.0000',
  KEY `fk_pelayanan_rm` (`no_daftar`),
  KEY `fk_pelayanan_tindakan` (`no_tindakan`),
  CONSTRAINT `fk_pelayanan_rm` FOREIGN KEY (`no_daftar`) REFERENCES `rekam_medis` (`no_daftar`),
  CONSTRAINT `fk_pelayanan_tindakan` FOREIGN KEY (`no_tindakan`) REFERENCES `tindakan` (`no_tindakan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.pelayanan_tindakan: ~0 rows (approximately)
/*!40000 ALTER TABLE `pelayanan_tindakan` DISABLE KEYS */;
/*!40000 ALTER TABLE `pelayanan_tindakan` ENABLE KEYS */;


# Dumping structure for table medrec.perawat
CREATE TABLE IF NOT EXISTS `perawat` (
  `no_perawat` varchar(7) NOT NULL DEFAULT '',
  `nm_perawat` varchar(45) NOT NULL DEFAULT '',
  `tgl_kerja_per` date DEFAULT '0000-00-00',
  `per_spesialis` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`no_perawat`),
  KEY `fk_perawat_spesialis` (`per_spesialis`) USING BTREE,
  CONSTRAINT `fk_perawat_spesialis` FOREIGN KEY (`per_spesialis`) REFERENCES `spesialis` (`id_spesialis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.perawat: ~0 rows (approximately)
/*!40000 ALTER TABLE `perawat` DISABLE KEYS */;
/*!40000 ALTER TABLE `perawat` ENABLE KEYS */;


# Dumping structure for table medrec.rekam_medis
CREATE TABLE IF NOT EXISTS `rekam_medis` (
  `no_daftar` varchar(12) NOT NULL DEFAULT '',
  `no_rm` varchar(6) NOT NULL DEFAULT '',
  `no_staf` varchar(7) NOT NULL,
  `bagian_spesialis` varchar(25) NOT NULL DEFAULT '',
  `id_jaminan` varchar(20) NOT NULL DEFAULT '',
  `no_dokter` varchar(7) NOT NULL DEFAULT '',
  `no_perawat` varchar(7) DEFAULT '',
  `nadi` int(10) DEFAULT '0',
  `temperatur` int(10) DEFAULT '0',
  `pernapasan` int(10) DEFAULT '0',
  `kesadaran` varchar(20) DEFAULT '',
  `anamnesa` text,
  `tinggi_bdn` float DEFAULT '0',
  `berat_bdn` float DEFAULT '0',
  `tensi_darah` varchar(7) DEFAULT '',
  `diagnosis` text,
  `terapi` text,
  `status` varchar(20) NOT NULL DEFAULT 'Antri',
  `tgl_daftar` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`no_daftar`),
  KEY `fk_pasien_rm` (`no_rm`),
  KEY `fk_staf_rm` (`no_staf`),
  KEY `fk_jaminan_rm` (`id_jaminan`),
  KEY `fk_dokter_rm` (`no_dokter`),
  KEY `fk_perawat_rm` (`no_perawat`),
  KEY `fk_unit_rs_rm` (`bagian_spesialis`) USING BTREE,
  CONSTRAINT `fk_dokter_rm` FOREIGN KEY (`no_dokter`) REFERENCES `dokter` (`no_dokter`),
  CONSTRAINT `fk_jaminan_rm` FOREIGN KEY (`id_jaminan`) REFERENCES `jaminan` (`id_jaminan`),
  CONSTRAINT `fk_pasien_rm` FOREIGN KEY (`no_rm`) REFERENCES `pasien` (`no_rm`),
  CONSTRAINT `fk_perawat_rm` FOREIGN KEY (`no_perawat`) REFERENCES `perawat` (`no_perawat`),
  CONSTRAINT `fk_spesialis_rm` FOREIGN KEY (`bagian_spesialis`) REFERENCES `spesialis` (`id_spesialis`),
  CONSTRAINT `fk_staf_rm` FOREIGN KEY (`no_staf`) REFERENCES `staf` (`no_staf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.rekam_medis: ~0 rows (approximately)
/*!40000 ALTER TABLE `rekam_medis` DISABLE KEYS */;
/*!40000 ALTER TABLE `rekam_medis` ENABLE KEYS */;


# Dumping structure for table medrec.resep
CREATE TABLE IF NOT EXISTS `resep` (
  `no_resep` varchar(9) NOT NULL,
  `no_daftar` varchar(12) NOT NULL,
  `tgl_resep` date NOT NULL,
  PRIMARY KEY (`no_resep`),
  KEY `fk_rekam_medis` (`no_daftar`),
  CONSTRAINT `fk_rekam_medis` FOREIGN KEY (`no_daftar`) REFERENCES `rekam_medis` (`no_daftar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.resep: ~0 rows (approximately)
/*!40000 ALTER TABLE `resep` DISABLE KEYS */;
/*!40000 ALTER TABLE `resep` ENABLE KEYS */;


# Dumping structure for table medrec.resep_dt
CREATE TABLE IF NOT EXISTS `resep_dt` (
  `no_resep` varchar(9) NOT NULL,
  `id_obat` varchar(45) NOT NULL,
  `satuan_kons` varchar(25) NOT NULL,
  `dosis_kons` varchar(20) NOT NULL,
  `jumlah` int(2) NOT NULL,
  KEY `fk_resep` (`no_resep`),
  KEY `fk_obat` (`id_obat`),
  CONSTRAINT `fk_obat` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`),
  CONSTRAINT `fk_resep` FOREIGN KEY (`no_resep`) REFERENCES `resep` (`no_resep`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.resep_dt: ~0 rows (approximately)
/*!40000 ALTER TABLE `resep_dt` DISABLE KEYS */;
/*!40000 ALTER TABLE `resep_dt` ENABLE KEYS */;


# Dumping structure for table medrec.spesialis
CREATE TABLE IF NOT EXISTS `spesialis` (
  `id_spesialis` varchar(10) NOT NULL,
  `nm_spesialis` varchar(45) NOT NULL,
  `tarif_konsul` int(6) unsigned NOT NULL,
  PRIMARY KEY (`id_spesialis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.spesialis: ~0 rows (approximately)
/*!40000 ALTER TABLE `spesialis` DISABLE KEYS */;
/*!40000 ALTER TABLE `spesialis` ENABLE KEYS */;


# Dumping structure for table medrec.staf
CREATE TABLE IF NOT EXISTS `staf` (
  `no_staf` varchar(7) NOT NULL DEFAULT '',
  `nm_staf` varchar(45) NOT NULL DEFAULT '',
  `alamat_staf` text,
  PRIMARY KEY (`no_staf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.staf: ~0 rows (approximately)
/*!40000 ALTER TABLE `staf` DISABLE KEYS */;
/*!40000 ALTER TABLE `staf` ENABLE KEYS */;


# Dumping structure for table medrec.tindakan
CREATE TABLE IF NOT EXISTS `tindakan` (
  `no_tindakan` varchar(9) NOT NULL DEFAULT '' COMMENT 'TIND.0000',
  `nm_tindakan` varchar(45) NOT NULL DEFAULT '',
  `tindakan_spesialis` varchar(10) NOT NULL DEFAULT '',
  `ket_tindakan` text,
  PRIMARY KEY (`no_tindakan`),
  KEY `fk_tindakan_spesialis` (`tindakan_spesialis`),
  CONSTRAINT `fk_tindakan_spesialis` FOREIGN KEY (`tindakan_spesialis`) REFERENCES `spesialis` (`id_spesialis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table medrec.tindakan: ~0 rows (approximately)
/*!40000 ALTER TABLE `tindakan` DISABLE KEYS */;
/*!40000 ALTER TABLE `tindakan` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
