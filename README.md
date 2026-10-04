<div align="center">

<!-- Animated Header Banner -->
<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1117,50:161b22,100:0d1117&height=220&section=header&text=GitHub%20Developer%20Insights&fontSize=42&fontColor=58a6ff&fontAlignY=35&desc=Discover%20%E2%80%A2%20Search%20%E2%80%A2%20Compare%20%E2%80%A2%20Track%20%E2%80%A2%20Manage&descSize=18&descColor=8b949e&descAlignY=55&animation=fadeIn" width="100%"/>

<!-- Badges Row 1 — Tech Stack -->
<p>
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Picocli-4.7.7-5865F2?style=for-the-badge" alt="Picocli"/>
  <img src="https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
</p>

<!-- Badges Row 2 — Status & Meta -->
<p>
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-MIT-22c55e?style=for-the-badge" alt="License"/></a>
  <img src="https://img.shields.io/badge/Platform-CLI-0ea5e9?style=for-the-badge&logo=windowsterminal&logoColor=white" alt="CLI"/>
  <img src="https://img.shields.io/badge/API-GitHub%20REST-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub API"/>
</p>

<!-- Short Tagline -->
<p><em>A powerful terminal-first application for exploring the GitHub ecosystem — built with Spring Boot & Picocli.</em></p>

</div>

---

## 🎯 Project Overview

**GitHub Developer Insights** is a Spring Boot command-line application that integrates with the **GitHub REST API** to help developers discover, search, compare, and monitor public repositories directly from the terminal.

The application combines:

- 🧩 **Picocli** for CLI commands and argument parsing
- 🌱 **Spring Boot** for dependency injection and application configuration
- 🌐 **Spring RestClient** for GitHub API communication
- 📦 **Jackson** for JSON-to-DTO mapping
- 🗃️ **Spring Data JPA + MySQL** for persistent favorites and star snapshots
- 🧪 **JUnit 5 + Mockito** for automated testing

---

## ✨ Features

<table>
  <tr>
    <td align="center" width="80">🔥</td>
    <td><strong>Trending Repos</strong></td>
    <td>Discover trending repositories filtered by duration and language</td>
  </tr>
  <tr>
    <td align="center">🔎</td>
    <td><strong>Smart Search</strong></td>
    <td>Search GitHub repos with sorting, ordering, and language filters</td>
  </tr>
  <tr>
    <td align="center">📋</td>
    <td><strong>Repository Details</strong></td>
    <td>View comprehensive details of any public repository</td>
  </tr>
  <tr>
    <td align="center">⚖️</td>
    <td><strong>Compare Repos</strong></td>
    <td>Side-by-side comparison table of two repositories</td>
  </tr>
  <tr>
    <td align="center">⭐</td>
    <td><strong>Star Tracking</strong></td>
    <td>Track star count over time with snapshots persisted to MySQL</td>
  </tr>
  <tr>
    <td align="center">📌</td>
    <td><strong>Favorites</strong></td>
    <td>Save, list, and remove favorite repositories locally</td>
  </tr>
</table>

---

## 🏛️ Architecture

The application follows a **layered architecture**:

```text
CLI Layer (Picocli Commands)
        │
        ▼
  Service Layer (Business Logic)
        │
   ┌────┴────┐
   ▼         ▼
GitHub    JPA Repository
API Client   │
   │         ▼
   ▼       MySQL
GitHub
REST API
```

This separation keeps **command parsing**, **business logic**, **external API communication**, and **database persistence** independent.

<details>
<summary><b>📂 Detailed Package Structure</b></summary>

```
github-developer-insights/
├── cli/                    # Picocli command definitions
│   ├── GithubInsightsCommand.java      # Root CLI command
│   ├── TrendingCommand.java            # trending subcommand
│   ├── SearchCommand.java              # search subcommand
│   ├── RepositoryCommand.java          # repository subcommand
│   ├── CompareCommand.java             # compare subcommand
│   ├── StarsCommand.java               # stars subcommand
│   ├── FavoriteCommand.java            # favorite subcommand
│   ├── RemoveFavoriteCommand.java      # favorite remove subcommand
│   ├── ListFavoriteCommand.java        # favorite list subcommand
│   └── util/
│       ├── CliErrorHandler.java        # HTTP error code handling
│       ├── ConsoleFormatter.java       # Console output formatting
│       └── InputValidator.java         # Input validation utilities
├── client/
│   └── GitHubApiClient.java           # REST client for GitHub API
├── dto/
│   ├── RepositoryDto.java             # GitHub repository data model
│   └── SearchResponseDto.java         # Search response wrapper
├── entity/
│   ├── Favorite.java                  # JPA entity for favorites
│   └── StarSnapshot.java             # JPA entity for star snapshots
├── exception/
│   └── GitHubApiException.java        # Custom exception with HTTP status
├── repository/
│   ├── FavoriteRepository.java        # Spring Data JPA repository
│   └── StarSnapshotRepository.java    # Spring Data JPA repository
└── service/
    ├── TrendingService.java           # Trending repos business logic
    ├── SearchService.java             # Search business logic
    ├── RepositoryService.java         # Repository details logic
    ├── CompareService.java            # Comparison logic
    ├── StarTrackingService.java       # Star tracking & history
    └── FavoriteService.java           # Favorites management
```

</details>

---

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|:---:|:---|:---|
| ☕ | **Java 21** | Modern Java with latest language features |
| 🌱 | **Spring Boot 4.1.1** | Application framework & dependency injection |
| 🗃️ | **Spring Data JPA** | Database persistence layer |
| 🌐 | **Spring Web (RestClient)** | HTTP client for GitHub API |
| ⌨️ | **Picocli 4.7.7** | Command-line argument parsing framework |
| 🐬 | **MySQL** | Relational database for favorites & star snapshots |
| 📦 | **Maven** | Build tool & dependency management |

---

## 📦 Prerequisites

Before running the application, ensure you have:

| Requirement | Version | Download |
|:---|:---|:---|
| **Java** | 21 or higher | [Adoptium](https://adoptium.net/) |
| **Maven** | 3.9+ | [Apache Maven](https://maven.apache.org/download.cgi) |
| **MySQL** | 8.0+ | [MySQL Downloads](https://dev.mysql.com/downloads/) |

---

## 🚀 Getting Started

### 1️⃣ Clone the repository

```bash
git clone https://github.com/akkiiop/github-developer-insights.git
cd github-developer-insights/github-developer-insights
```

### 2️⃣ Set up the MySQL database

```sql
CREATE DATABASE github_insights;
```

### 3️⃣ Configure database credentials

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/github_insights
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4️⃣ Build the project

```bash
./mvnw clean package -DskipTests
```

### 5️⃣ Run the application

```bash
java -jar target/github-developer-insights-0.0.1-SNAPSHOT.jar <command> [options]
```

---

## 📖 Usage & Commands

### 🔥 Trending Repositories

Discover trending repositories created within a specified time period.

```bash
# Default: top 10 trending repos from the past week
java -jar target/*.jar trending

# Trending repos from the past month, limited to 5
java -jar target/*.jar trending --duration month --limit 5

# Trending Python repos from the past day
java -jar target/*.jar trending --duration day --language Python
```

<details>
<summary><b>Options & Example Output</b></summary>

**Options:**

| Option | Description | Default | Values |
|:---|:---|:---|:---|
| `--duration` | Time period to search | `week` | `day`, `week`, `month`, `year` |
| `--limit` | Max repositories to display | `10` | `1` – `100` |
| `--language` | Filter by programming language | _none_ | Any language name |

**Example Output:**

```
GitHub Trending Repositories
----------------------------------
Duration: week
Limit: 5
Search Query: created:>2026-09-27 stars:>100

Repositories Found: 12,345

facebook/react
Language: JavaScript
Stars: 230000
----------------------------------
```

</details>

---

### 🔎 Search Repositories

Search for GitHub repositories with advanced filtering and sorting.

```bash
# Search for machine learning repos
java -jar target/*.jar search --query "machine learning"

# Search for Rust web frameworks, sorted by forks
java -jar target/*.jar search --query "web framework" --language Rust --sort forks --limit 5

# Search with ascending order
java -jar target/*.jar search --query "cli tool" --sort updated --order asc
```

<details>
<summary><b>Options & Example Output</b></summary>

**Options:**

| Option | Description | Default | Values |
|:---|:---|:---|:---|
| `--query` | Search query *(required)* | — | Any text |
| `--limit` | Max results | `10` | `1` – `100` |
| `--language` | Filter by language | _none_ | Any language name |
| `--sort` | Sort criteria | `stars` | `stars`, `forks`, `updated` |
| `--order` | Sort order | `desc` | `asc`, `desc` |

**Example Output:**

```
GitHub Repository Search
----------------------------------
Query: web framework
Language: Rust
Sort: forks | Order: desc
Repositories Found: 845

nickel-org/nickel.rs
Language: Rust
Stars: 3,200
Forks: 180
----------------------------------
```

</details>

---

### 📋 Repository Details

View detailed information about a specific repository.

```bash
java -jar target/*.jar repository spring-projects/spring-boot
java -jar target/*.jar repository torvalds/linux
```

<details>
<summary><b>Example Output</b></summary>

```
Repository Details
----------------------------------
Name: spring-boot
Full Name: spring-projects/spring-boot
Description: Spring Boot helps you to create Spring-powered applications
Language: Java
Stars: 75000
Forks: 40000
Open Issues: 500
Default Branch: main
Archived: false
Fork: false
URL: https://github.com/spring-projects/spring-boot
```

</details>

---

### ⚖️ Compare Repositories

Compare two repositories side-by-side with a formatted comparison table.

```bash
java -jar target/*.jar compare facebook/react angular/angular
java -jar target/*.jar compare spring-projects/spring-boot quarkusio/quarkus
```

<details>
<summary><b>Example Output</b></summary>

```
================================================================
                    REPOSITORY COMPARISON
================================================================

Metric                         react         angular      Difference
----------------------------------------------------------------
Stars                        230,000        96,000       -134,000
Forks                         47,000        25,000        -22,000
Open Issues                    1,200           850          -350
----------------------------------------------------------------
Language                  JavaScript      TypeScript             -
Archived                       false           false             -
Fork                           false           false             -
================================================================
```

</details>

---

### ⭐ Star Tracking

Track star growth of a repository over time. Each run saves a snapshot to the database.

```bash
# Save a star snapshot and show growth summary
java -jar target/*.jar stars torvalds/linux

# Save a snapshot and show complete star history
java -jar target/*.jar stars torvalds/linux --history
```

<details>
<summary><b>Options & Example Output</b></summary>

**Options:**

| Option | Description |
|:---|:---|
| `--history` | Display complete star history table instead of growth summary |

**Growth Summary:**

```
Star Growth
==================================================
Repository: torvalds/linux

First Snapshot : 180000
Latest Snapshot: 185000
Growth         : +5000
Snapshots      : 12
First Captured : 2026-09-01T10:30:00
Latest Captured: 2026-10-03T20:00:00
```

**History Table (`--history`):**

```
Star History
==================================================
Repository: torvalds/linux

Captured At               Stars
------------------------------------------
2026-09-01T10:30:00       180000
2026-09-15T14:00:00       182000
2026-10-03T20:00:00       185000
------------------------------------------
Total Growth: +5000
Snapshots: 3
```

</details>

---

### 📌 Favorites Management

Save repositories to your local favorites for quick access.

```bash
# Add a repository to favorites
java -jar target/*.jar favorite torvalds/linux

# List all favorites
java -jar target/*.jar favorite list

# Remove a repository from favorites
java -jar target/*.jar favorite remove torvalds/linux
```

<details>
<summary><b>Example Output</b></summary>

**List Favorites:**

```
======================================================================
 Favorite Repositories
======================================================================

ID    Repository                          Added At
----------------------------------------------------------------------
1     torvalds/linux                      2026-10-01 14:30:00
2     spring-projects/spring-boot         2026-10-02 09:15:00
3     facebook/react                      2026-10-03 20:00:00
----------------------------------------------------------------------

Total Favorites: 3
```

</details>

---

## ⚙️ Configuration

All configuration is in `src/main/resources/application.properties`:

```properties
# GitHub API
github.api.base-url=https://api.github.com
github.api.default-limit=10
github.api.max-limit=100
github.api.min-stars=100

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/github_insights
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
```

---

## 🧪 Testing

The project includes automated tests using **JUnit 5** and **Mockito**.

### Unit Tests

| Test Class | What it validates |
|:---|:---|
| `InputValidatorTest` | Validates repository format, limit range, duration, sort, and order inputs |
| `FavoriteServiceTest` | Tests favorite creation and duplicate-favorite handling using Mockito mocks |

### Integration Tests

| Test Class | What it validates |
|:---|:---|
| `GithubDeveloperInsightsApplicationTests` | Verifies Spring Boot application context loads successfully |
| `FavoriteServiceIntegrationTest` | Verifies the Spring integration-test configuration |

### Running Tests

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=InputValidatorTest
./mvnw test -Dtest=FavoriteServiceTest
```

---

## 🗄️ Database Schema

The application auto-creates two tables via JPA (`ddl-auto=update`):

<table>
<tr><td>

**`favorite`**

| Column | Type | Description |
|:---|:---|:---|
| `id` | BIGINT (PK) | Auto-generated ID |
| `owner` | VARCHAR | Repository owner |
| `repository_name` | VARCHAR | Repository name |
| `created_at` | DATETIME | When the favorite was added |

</td><td>

**`star_snapshot`**

| Column | Type | Description |
|:---|:---|:---|
| `id` | BIGINT (PK) | Auto-generated ID |
| `owner` | VARCHAR | Repository owner |
| `repository_name` | VARCHAR | Repository name |
| `stars` | INT | Star count at snapshot time |
| `captured_at` | DATETIME | When the snapshot was taken |

</td></tr>
</table>

---

## 🔧 Error Handling

The CLI provides user-friendly error messages for common GitHub API errors:

| HTTP Code | Message |
|:---:|:---|
| `400` | Invalid request |
| `401` | GitHub authentication failed |
| `403` | Access denied or rate limit reached |
| `404` | Repository or resource not found |
| `422` | GitHub could not process the request |
| `5xx` | GitHub server error, try again later |
| `-1` | Could not connect to GitHub API |

---

## 📁 Project Structure

```
github-developer-insights/          ← Repository root
├── README.md
├── LICENSE
├── .gitignore
└── github-developer-insights/      ← Spring Boot module
    ├── pom.xml
    ├── mvnw / mvnw.cmd             ← Maven wrapper
    └── src/
        ├── main/
        │   ├── java/               ← Application source code
        │   └── resources/
        │       └── application.properties
        └── test/
            └── java/               ← Unit & integration tests
```

---

## 🤝 Contributing

Contributions are welcome! Here's how to get started:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👤 Author

**akkiiop** — [GitHub Profile](https://github.com/akkiiop)

---

<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1117,50:161b22,100:0d1117&height=100&section=footer" width="100%"/>

<sub>⭐ If you found this project useful, consider giving it a star!</sub>

</div>
