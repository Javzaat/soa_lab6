Lab06 – JSON Service ба SOAP Service интеграци

Төслийн тухай

Энэхүү лабораторийн ажил нь Service-Oriented Architecture (SOA) зарчмыг ашиглан JSON REST Service болон SOAP Authentication Service хоорондын интеграцчиллыг хэрэгжүүлэх зорилготой.

Систем нь 3 үндсэн component-оос бүрдэнэ:
	•	User SOAP Service – хэрэглэгчийн authentication
	•	User JSON Service – хэрэглэгчийн profile management
	•	Frontend Application – register, login, profile UI

Authentication логик нь JSON service дээр бус SOAP service дээр төвлөрсөн бөгөөд JSON service нь middleware ашиглан SOAP service-ээс token validate хийлгэдэг.



System Architecture

Frontend → JSON Service → SOAP ValidateToken
	1.	Хэрэглэгч frontend дээр login хийнэ
	2.	SOAP service дээр LoginUser operation дуудагдана
	3.	SOAP service authentication token үүсгэнэ
	4.	Token frontend-д хадгалагдана (localStorage)
	5.	Profile API дуудах үед JSON service middleware SOAP service-ээс token шалгуулна
	6.	Token хүчинтэй бол REST request зөвшөөрөгдөнө
Project Structure

lab6
│
├── user-soap-service
│   └── SOAP Authentication Service
│
├── user-json-service
│   └── REST User Profile Service
│
└── frontend-app
    └── Simple HTML / JavaScript frontend 


SOAP Authentication Service

Technology
	•	Java 21
	•	Spring Boot
	•	Spring Web Services
	•	Maven

SOAP Operations


RegisterUser- шинэ хэрэглэгч бүртгэх
LoginUser- хэрэглэгчийг баталгаажуулж token үүсгэх
ValidateToken- authentication token шалгах 


WSDL

http://localhost:8081/ws/auth.wsdl

JSON User Profile Service

REST API ашиглан хэрэглэгчийн profile мэдээллийг удирдана.

Technology
	•	Java 21
	•	Spring Boot
	•	REST API
	•	Lombok
	
	
POST- /users- шинэ profile үүсгэх
GET- /users- бүх profile авах
GET- /users/{id}- profile мэдээлэл авах
PUT- /users/{id}- profile шинэчлэх
DELETE- /users/{id}- profile устгах

Authentication middleware нь SOAP service-ээс token validate хийдэг.

Frontend Application

Simple frontend application нь HTML болон JavaScript ашиглан SOAP болон REST service-үүдийг тестлэхэд ашиглагдана.

Pages:
	•	Register Page
	•	Login Page
	•	Profile Page

Login амжилттай бол token-ийг browser-ийн localStorage дээр хадгална.


Authentication Flow
	1.	Frontend → SOAP RegisterUser
	2.	Frontend → SOAP LoginUser
	3.	SOAP → token буцаана
	4.	Frontend → token хадгална
	5.	Frontend → JSON REST request
	6.	JSON middleware → SOAP ValidateToken
	7.	SOAP → token хүчинтэй эсэхийг шалгана
	8.	JSON → request зөвшөөрөх эсвэл татгалзах


Running the Project
1. SOAP Service

cd user-soap-service
./mvnw spring-boot:run

http://localhost:8081

2. JSON Service

cd user-json-service
./mvnw spring-boot:run

http://localhost:8080

3. Frontend

Frontend folder-ийг Live Server эсвэл static server ашиглан ажиллуулна.

http://127.0.0.1:5500

Security

Authentication логик JSON service дээр хэрэгжихгүй.

JSON service:
	•	Authorization header-ээс token уншина
	•	SOAP service-рүү ValidateToken request илгээнэ
	•	Хэрэв token хүчинтэй бол request зөвшөөрнө
	•	Хэрэв token буруу бол 401 Unauthorized буцаана

Technologies Used
	•	Java 21
	•	Spring Boot
	•	Spring Web Services
	•	REST API
	•	SOAP
	•	Maven
	•	HTML
	•	JavaScript

