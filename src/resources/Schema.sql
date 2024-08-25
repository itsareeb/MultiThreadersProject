create table Employee (empId INT AUTO_INCREMENT PRIMARY KEY, role ENUM('user', 'doctor', 'admin') NOT NULL, empName VARCHAR(30) NOT NULL, password VARCHAR(100) NOT NULL, isActive BOOLEAN DEFAULT(TRUE), contact VARCHAR(10) NOT NULL, email VARCHAR(30) NOT NULL UNIQUE, createdAt DATETIME DEFAULT CURRENT_TIMESTAMP, updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);
create table Doctor (doctorId INT PRIMARY KEY, qualification VARCHAR(50) NOT NULL, specialization VARCHAR(50) NOT NULL, department ENUM ('emergency', 'general') DEFAULT('general'), FOREIGN KEY (doctorId) REFERENCES Employee(empId));
create table User (userId INT PRIMARY KEY, department ENUM('general', 'emergency') DEFAULT('general'), shift ENUM('day', 'night') NOT NULL, FOREIGN KEY (userId) REFERENCES Employee(empId));
create table Patient (patientId INT AUTO_INCREMENT PRIMARY KEY, patientName VARCHAR(30) NOT NULL, gender ENUM('male', 'female', 'other') NOT NULL, age INT NOT NULL, contact VARCHAR(10) NOT NULL, email VARCHAR(30), userId INT NOT NULL, UNIQUE(patientName, contact), FOREIGN KEY (userId) REFERENCES User(userId), createdAt DATETIME DEFAULT CURRENT_TIMESTAMP, updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);
CREATE TABLE Schedule (
                          scheduleId INT AUTO_INCREMENT PRIMARY KEY,
                          doctorId INT NOT NULL,
                          shiftno INT NOT NULL,
                          date DATE NOT NULL,
                          isAvailable BOOLEAN NOT NULL,
                          UNIQUE(doctorId, shiftno, date),
                          FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId)
);

CREATE TABLE Slots (
                       scheduleId INT NOT NULL,
                       slotno INT NOT NULL,
                       slotTime TIME NOT NULL,
                       isBooked BOOLEAN DEFAULT(FALSE),
                       FOREIGN KEY (scheduleId) REFERENCES Schedule(scheduleId),
                       PRIMARY KEY (scheduleId, slotno)
);

create table Appointments (
                              appId INT AUTO_INCREMENT PRIMARY KEY,
                              userId INT NOT NULL,
                              patientId INT NOT NULL,
                              scheduleId INT NOT NULL,
                              slotno INT NOT NULL,
                              status ENUM ('pending', 'completed', 'cancelled') DEFAULT('pending'),
                              FOREIGN KEY (userId) REFERENCES User(userId),
                              FOREIGN KEY (scheduleId) REFERENCES Schedule(scheduleId),
                              FOREIGN KEY (patientId) REFERENCES Patient(patientId),
                              createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                              updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


create table Medications (medicationId INT AUTO_INCREMENT PRIMARY KEY, appId INT NOT NULL, name VARCHAR(30) NOT NULL, dosage VARCHAR(10) NOT NULL, instructions VARCHAR(50), FOREIGN KEY (appId) references Appointments(appId));
create table Tests(testId INT AUTO_INCREMENT PRIMARY KEY, appId INT NOT NULL, name VARCHAR(30) NOT NULL, FOREIGN KEY (appId) REFERENCES Appointments(appId));