# ENSIM_24H_du_code
<br />
<div align="center">

<h3 align="center">API for 24h du code Le Mans</h3>

  <p align="center">
    It is an API for evenement 24h du code Le Mans, we need to create Back-end for feature mobile applications(IOS, Android). These applications allow to simplify connection between organisator and participants.
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

It is an API for evenement 24h du code Le Mans, we need to create Back-end for feature mobile applications(IOS, Android). These applications allow to simplify connection between organisator and participants.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* Java
* Java Spring Boot (Framework)
* postgresql (Database)
* maven (Build system)


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started
You need to configure your own postgresql local, after you need to modify:
resources/application.yml

Then you need to use IDE(I advice to use intellij idea) or Maven directly in terminal for start application

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

All endpoints:
* POST - /api/v1/auth/login
* POST - /api/v1/auth/register

* POST - /api/v1/equipe        | create team
* GET - /api/v1/equipe/all     | return list of teams
* GET - /api/v1/equipe/{id}    | return information about team with id == {id} 
* POST - /api/v1/equipe/join   | join team
* POST - /api/v1/equipe/leave  | leave team

* GET - /api/v1/user           | return list of users

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Users
    - [ ] Separate Users (You can do it )
        - [ ] Porteur du sujet
        - [ ] Admin
        - [ ] participant
- [ ] Sujets
- [x] Teams
    - [x] creation
    - [x] join
    - [x] leave
    - [ ] delete
- [ ] All other things what you can find in rapport 


<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTACT -->
## Contact

Alex Kotov - o.o.kotov1999@gmail.com

<p align="right">(<a href="#readme-top">back to top</a>)</p>
