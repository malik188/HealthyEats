-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: 34.101.128.76
-- Generation Time: 31 Mei 2023 pada 08.05
-- Versi Server: 8.0.26-google
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db-healthy-eats`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `table_food`
--

CREATE TABLE `table_food` (
  `food_id` int NOT NULL,
  `food_name` varchar(255) NOT NULL,
  `food_calories` int NOT NULL,
  `food_protein` decimal(5,5) NOT NULL,
  `food_calcium` decimal(5,5) NOT NULL,
  `food_fat` decimal(5,5) NOT NULL,
  `food_carbo` decimal(5,5) NOT NULL,
  `food_vitamins` decimal(5,5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `table_object`
--

CREATE TABLE `table_object` (
  `object_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `date_captured` date DEFAULT NULL,
  `user_id` int NOT NULL,
  `food_id` int NOT NULL,
  `plan_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `table_plan`
--

CREATE TABLE `table_plan` (
  `plan_id` int NOT NULL,
  `plan_name` varchar(255) NOT NULL,
  `plan_goal` varchar(255) NOT NULL,
  `plan_activity` varchar(255) NOT NULL,
  `calories_target` int NOT NULL,
  `calories_consume` int DEFAULT '0',
  `user_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `table_user`
--

CREATE TABLE `table_user` (
  `user_id` int NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `user_pass` varchar(255) NOT NULL,
  `user_age` int DEFAULT NULL,
  `user_gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `user_height` int DEFAULT NULL,
  `user_weight` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `table_food`
--
ALTER TABLE `table_food`
  ADD PRIMARY KEY (`food_id`);

--
-- Indexes for table `table_object`
--
ALTER TABLE `table_object`
  ADD PRIMARY KEY (`object_id`),
  ADD KEY `object_fk_user` (`user_id`),
  ADD KEY `object_fk_food` (`food_id`),
  ADD KEY `object_fk_plan` (`plan_id`);

--
-- Indexes for table `table_plan`
--
ALTER TABLE `table_plan`
  ADD PRIMARY KEY (`plan_id`),
  ADD KEY `plan_fk_user` (`user_id`);

--
-- Indexes for table `table_user`
--
ALTER TABLE `table_user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `table_food`
--
ALTER TABLE `table_food`
  MODIFY `food_id` int NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `table_object`
--
ALTER TABLE `table_object`
  MODIFY `object_id` int NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `table_plan`
--
ALTER TABLE `table_plan`
  MODIFY `plan_id` int NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `table_user`
--
ALTER TABLE `table_user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT;
--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `table_object`
--
ALTER TABLE `table_object`
  ADD CONSTRAINT `object_fk_food` FOREIGN KEY (`food_id`) REFERENCES `table_food` (`food_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `object_fk_plan` FOREIGN KEY (`plan_id`) REFERENCES `table_plan` (`plan_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `object_fk_user` FOREIGN KEY (`user_id`) REFERENCES `table_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Ketidakleluasaan untuk tabel `table_plan`
--
ALTER TABLE `table_plan`
  ADD CONSTRAINT `plan_fk_user` FOREIGN KEY (`user_id`) REFERENCES `table_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
