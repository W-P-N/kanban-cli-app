# Kanban CLI

```
     _   __            _                   _____  _     _____
    | | / /           | |                 /  __ \| |   |_   _|
    | |/ /  __ _ _ __ | |__   __ _ _ __   | /  \/| |     | |
    |    \ / _` | '_ \| '_ \ / _` | '_ \  | |    | |     | |
    | |\  \ (_| | | | | |_) | (_| | | | | | \__/\| |_____| |_
    \_| \_/\__,_|_| |_|_.__/ \__,_|_| |_|  \____/\_____/\___/
```

A lightweight, open-source command-line Kanban board manager. Organize your work with boards and tasks, and track progress from `TODO → DOING → FINISHED` — all from your terminal.

---

## Features

- Create and manage multiple Kanban boards
- Add, rename, and delete tasks within boards
- Track task progress through status transitions (`TODO`, `DOING`, `FINISHED`)
- Persistent storage — your boards and tasks survive between sessions
- Extensible command architecture — easy to add new commands via config

---

## Requirements

- Java 17 or higher
- Maven 3.8+ (for building from source)

---

## Installation

### Option 1 — Download JAR (Recommended)

Download the latest release JAR from the [Releases](https://github.com/yourusername/kanban-cli/releases) page and run:

```bash
java -jar kanban-cli.jar
```

### Option 2 — Build from Source

```bash
git clone https://github.com/yourusername/kanban-cli.git
cd kanban-cli
mvn clean package
java -jar target/kanban-cli-1.0-SNAPSHOT.jar
```

---

## Usage

Once the app starts, you'll see the prompt:

```
> 
```

When a board is active, the prompt shows the board name:

```
(MyBoard) > 
```

---

## Command Reference

### Utility Commands

| Command | Description |
|---------|-------------|
| `help`  | Display all available commands |
| `quit`  | Save and exit the application |

### Board Commands

| Command | Description |
|---------|-------------|
| `board list` | List all boards with their IDs |
| `board create <boardName>` | Create a new board |
| `board delete <boardId>` | Delete a board by ID |
| `board rename <boardId> <newName>` | Rename an existing board |
| `board open <boardId>` | Open a board to work on tasks |
| `board close` | Close the currently active board |

### Task Commands

| Command | Description |
|---------|-------------|
| `task list` | List all tasks in the active board |
| `task add <taskName>` | Add a new task to the active board |
| `task delete <taskId>` | Delete a task by ID |
| `task rename <taskId> <newName>` | Rename a task |
| `task advance <taskId>` | Move task status forward (`TODO → DOING → FINISHED`) |
| `task revert <taskId>` | Move task status backward (`FINISHED → DOING → TODO`) |

---

## Data Storage

Your boards and tasks are automatically saved to:

| OS | Path |
|----|------|
| Linux / macOS | `~/.kanban-cli/persistence.json` |
| Windows | `C:\Users\<username>\.kanban-cli\persistence.json` |

Data is saved automatically when you `quit` the application.

---

## Project Structure

```
src/main/java/com/wpn/kanban/
├── cli/
│   ├── Main.java               # Entry point
│   ├── AppContext.java         # App lifecycle and shared resources
│   └── AppState.java           # App data (boards, tasks)
├── core/
│   ├── Board.java              # Board model
│   ├── Task.java               # Task model
│   └── Status.java             # Task status enum
├── parser/
│   ├── CommandParser.java      # Parses and dispatches commands
│   ├── CommandLoader.java      # Loads commands from config
│   ├── CommandGroup.java       # Groups related commands
│   ├── InputTokenizer.java     # Tokenizes raw input
│   └── ParsedCommand.java      # Structured command representation
│   └── commands/
│       ├── board/              # Board command implementations
│       ├── task/               # Task command implementations
│       └── util/               # Utility commands (help, quit)
└── persistence/
    └── PersistenceManager.java # Handles save/load of app state
```

---

## Adding New Commands

1. Create a new class implementing the `Command` interface:

```java
public class MyCommand implements Command {
    @Override
    public String getName() { return "mycommand"; }

    @Override
    public String getDescription() { return "Does something cool. Usage: mycommand"; }

    @Override
    public void execute(AppContext ctx, ParsedCommand cmd) {
        // your logic here
    }
}
```

2. Register it in `src/main/resources/commands.json`:

```json
{
  "mycommand": "com.wpn.kanban.parser.commands.MyCommand"
}
```

That's it — no changes to the parser needed.

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/my-feature`
3. Commit your changes: `git commit -m "Add my feature"`
4. Push to your branch: `git push origin feature/my-feature`
5. Open a Pull Request

Please ensure your code follows the existing architecture patterns and includes meaningful error handling.

---

## License

This project is licensed under the **GNU General Public License v3.0**. See the [LICENSE](LICENSE) file for details.

You are free to use, modify, and distribute this software under the terms of GPLv3. Any derivative work must also be released under GPLv3.

---

## Author

Built with ❤️ by [Parth Wani](https://github.com/W-P-N)