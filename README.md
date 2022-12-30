<a name="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/rezimaulana/elearning">
    <img src="assets/images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">E-Learning</h3>

  <p align="center">
    Online course and learning applications for students and lecturers
    <br />
    <a href="https://github.com/rezimaulana/elearning"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/rezimaulana/elearning">View Demo</a>
    ·
    <a href="https://github.com/rezimaulana/elearning/issues">Report Bug</a>
    ·
    <a href="https://github.com/rezimaulana/elearning/issues">Request Feature</a>
  </p>
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
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

The following is a brief description of the problem and system flow
* Requirements :
  ```sh
  1. User roles consist of students, teachers and super admins
  2. Students must register independently first
  3. Students can choose (enroll) available classes
  4. Students must attend first (and their attendance must be approved by the teacher) to be able to (download material or download and do quizzes / tests)
  5. The teacher adds material (start date, end date, material file), quiz (start date, end date, quiz file) and test (start date, end date, quiz file)
  6. Teachers can see the results of quizzes and tests and can immediately give their grades
  7. Private forums per class are created by students and are text only. And only the teacher and students can see and reply to each other in the forum.
  8. Required reports:
    a) Student attendance
    b) Student grades
    c) Schedule of Teaching and Learning Activities (KBM) for admin per teacher
  9. Super admin creates a class and assigns the teacher
  10. Teachers must first be registered by the super admin
  11. Materials and quizzes are daily in nature while tests are weekly
  ```
* Technology used :
  ```sh
  1. Java 8 Spring Boot
  2. Angular 12
  3. Postgresql 14
  4. JWTs
  5. Jasper Reports
  ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![Spring][Spring.io]][Spring-url]
* [![Angular][Angular.io]][Angular-url]
* [![Bootstrap][Bootstrap.com]][Bootstrap-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* Java
  ```sh
  Java 11 is recommended
  ```
* npm
  ```sh
  npm install npm@latest -g
  ```

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/rezimaulana/elearning.git
   ```
2. Install NPM packages on /frontend
   ```sh
   npm install
   ```
3. Build using maven on /backend
   ```sh
   mvn clean install
   ```
4. Run jar using java 11 /backend/target/
   ```sh
   java -jar filename.jar
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

Still working useful examples of how this project can be used. Additional screenshots, code examples and demos will be provided in this space. 

_Please refer here on the future to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Backend
    - [x] Database
    - [x] Master API
    - [ ] Transaction API
- [x] Frontend
    - [x] Page Layout
    - [x] Routing
    - [ ] Connect all feature

See the [open issues](https://github.com/rezimaulana/elearning/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the GPL-3.0 License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Maulana Rezi Rosadi - [@rezimaulana](https://twitter.com/rezimaulana) - rsazrm@gmail.com

Project Link: [https://github.com/rezimaulana/elearning](https://github.com/rezimaulana/elearning)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments


I've included a few of resources that i find helpful:

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/rezimaulana/elearning.svg?style=for-the-badge
[contributors-url]: https://github.com/rezimaulana/elearning/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/rezimaulana/elearning.svg?style=for-the-badge
[forks-url]: https://github.com/rezimaulana/elearning/network/members
[stars-shield]: https://img.shields.io/github/stars/rezimaulana/elearning.svg?style=for-the-badge
[stars-url]: https://github.com/rezimaulana/elearning/stargazers
[issues-shield]: https://img.shields.io/github/issues/rezimaulana/elearning.svg?style=for-the-badge
[issues-url]: https://github.com/rezimaulana/elearning/issues
[license-shield]: https://img.shields.io/github/license/rezimaulana/elearning.svg?style=for-the-badge
[license-url]: https://github.com/rezimaulana/elearning/blob/main/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/rezimaulana
[product-screenshot]: assets/images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
[Spring.io]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
