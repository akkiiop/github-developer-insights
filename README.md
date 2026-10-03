<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Picocli-4.7.7-blue?style=for-the-badge" alt="Picocli"/>
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" alt="License"/>
</p>

# 🔍 GitHub Developer Insights

A powerful **command-line application** built with **Spring Boot** and **Picocli** that provides real-time insights into GitHub repositories. Discover trending repos, search across GitHub, compare projects side-by-side, track star growth over time, and manage your personal favorites — all from the terminal.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🔥 **Trending Repos** | Discover trending repositories filtered by duration and language |
| 🔎 **Smart Search** | Search GitHub repos with sorting, ordering, and language filters |
| 📋 **Repository Details** | View comprehensive details of any public repository |
| ⚖️ **Compare Repos** | Side-by-side comparison table of two repositories |
| ⭐ **Star Tracking** | Track star count over time with snapshots persisted to MySQL |
| 📌 **Favorites** | Save, list, and remove favorite repositories locally |

---

## 🏗️ Architecture

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

---

## 🛠️ Tech Stack

- **Java 21** — Modern Java with latest language features
- **Spring Boot 4.1.1** — Application framework & dependency injection
- **Spring Data JPA** — Database persistence layer
- **Spring Web (RestClient)** — HTTP client for GitHub API
- **Picocli 4.7.7** — Command-line argument parsing framework
- **MySQL** — Relational database for favorites & star snapshots
- **Maven** — Build tool & dependency management

---

## 📦 Prerequisites

Before running the application, ensure you have:

- **Java 21** or higher — [Download](https://adoptium.net/)
- **Maven 3.9+** — [Download](https://maven.apache.org/download.cgi)
- **MySQL 8.0+** — [Download](https://dev.mysql.com/downloads/)

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/akkiiop/github-developer-insights.git
cd github-developer-insights/github-developer-insights
```

### 2. Set up the MySQL database

```sql
CREATE DATABASE github_insights;
```

### 3. Configure database credentials

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/github_insights
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Build the project

```bash
./mvnw clean package -DskipTests
```

### 5. Run the application

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

**Options:**

| Option | Description | Default | Values |
|---|---|---|---|
| `--duration` | Time period to search | `week` | `day`, `week`, `month`, `year` |
| `--limit` | Max repositories to display | `10` | `1` - `100` |
| `--language` | Filter by programming language | _none_ | Any language name |

**Example Output:**

```
GitHub Trending Repositories
----------------------------------
Duration: week
Limit: 5

Repositories Found: 12,345

facebook/react
Language: JavaScript
Stars: 230,000
----------------------------------
```

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

**Options:**

| Option | Description | Default | Values |
|---|---|---|---|
| `--query` | Search query *(required)* | — | Any text |
| `--limit` | Max results | `10` | `1` - `100` |
| `--language` | Filter by language | _none_ | Any language name |
| `--sort` | Sort criteria | `stars` | `stars`, `forks`, `updated` |
| `--order` | Sort order | `desc` | `asc`, `desc` |

---

### 📋 Repository Details

View detailed information about a specific repository.

```bash
java -jar target/*.jar repository spring-projects/spring-boot
java -jar target/*.jar repository torvalds/linux
```

**Example Output:**

```
Repository Details
----------------------------------
Name: spring-boot
Full Name: spring-projects/spring-boot
Description: Spring Boot helps you to create Spring-powered applications
Language: Java
Stars: 75,000
Forks: 40,000
Open Issues: 500
Default Branch: main
Archived: false
Fork: false
URL: https://github.com/spring-projects/spring-boot
```

---

### ⚖️ Compare Repositories

Compare two repositories side-by-side with a formatted comparison table.

```bash
java -jar target/*.jar compare facebook/react angular/angular
java -jar target/*.jar compare spring-projects/spring-boot quarkusio/quarkus
```

**Example Output:**

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

---

### ⭐ Star Tracking

Track star growth of a repository over time. Each run saves a snapshot to the database.

```bash
# Save a star snapshot and show growth summary
java -jar target/*.jar stars torvalds/linux

# Save a snapshot and show complete star history
java -jar target/*.jar stars torvalds/linux --history
```

**Options:**

| Option | Description |
|---|---|
| `--history` | Display complete star history table instead of growth summary |

**Example Output (Growth):**

```
Star Growth
==================================================
Repository: torvalds/linux

First Snapshot : 180,000
Latest Snapshot: 185,000
Growth         : +5,000
Snapshots      : 12
First Captured : 2026-09-01T10:30:00
Latest Captured: 2026-10-03T20:00:00
```

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

**Example Output (List):**

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

---

## ⚙️ Configuration

All configuration is in `src/main/resources/application.properties`:

```properties
# GitHub API
github.api.base-url=https://api.github.com
github.api.default-limit=10
github.api.max-limit=100
github.api.min-stars=100
github.api.connect-timeout=5000
github.api.read-timeout=10000

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

The project includes unit tests and integration tests:

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=InputValidatorTest
./mvnw test -Dtest=FavoriteServiceTest
```

| Test | Type | Description |
|---|---|---|
| `InputValidatorTest` | Unit | Validates input parsing for repos, limits, durations, sort & order |
| `FavoriteServiceTest` | Unit | Tests favorite add, remove, list, and duplicate handling |
| `FavoriteServiceIntegrationTest` | Integration | End-to-end favorite operations with database |
| `GithubDeveloperInsightsApplicationTests` | Integration | Spring Boot application context loading |

---

## 🗄️ Database Schema

The application auto-creates two tables via JPA (`ddl-auto=update`):

**`favorite`**

| Column | Type | Description |
|---|---|---|
| `id` | BIGINT (PK) | Auto-generated ID |
| `owner` | VARCHAR | Repository owner |
| `repository_name` | VARCHAR | Repository name |
| `created_at` | DATETIME | When the favorite was added |

**`star_snapshot`**

| Column | Type | Description |
|---|---|---|
| `id` | BIGINT (PK) | Auto-generated ID |
| `owner` | VARCHAR | Repository owner |
| `repository_name` | VARCHAR | Repository name |
| `stars` | INT | Star count at snapshot time |
| `captured_at` | DATETIME | When the snapshot was taken |

---

## 🔧 Error Handling

The CLI provides user-friendly error messages for common GitHub API errors:

| HTTP Code | Message |
|---|---|
| 400 | Invalid request |
| 401 | GitHub authentication failed |
| 403 | Access denied or rate limit reached |
| 404 | Repository or resource not found |
| 422 | GitHub could not process the request |
| 5xx | GitHub server error, try again later |
| -1 | Could not connect to GitHub API |

---

## 📁 Project Structure

```
github-developer-insights/
├── README.md
├── .gitignore
└── github-developer-insights/        # Spring Boot module
    ├── pom.xml
    ├── mvnw / mvnw.cmd               # Maven wrapper
    └── src/
        ├── main/
        │   ├── java/                  # Application source code
        │   └── resources/
        │       └── application.properties
        └── test/
            └── java/                  # Unit & integration tests
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

<p align="center">
  <sub>⭐ If you found this project useful, consider giving it a star!</sub>
</p>
