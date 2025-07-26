# Golden Radio IntelliJ Plugin

[![Version](https://img.shields.io/badge/version-2.0.0-blue.svg)](https://github.com/QQViaja/golden-radio-intellij-plugin)
[![IntelliJ Platform](https://img.shields.io/badge/IntelliJ%20Platform-2025.1+-orange.svg)](https://www.jetbrains.com/idea/)

<!-- Plugin description -->
<a href="https://plugins.jetbrains.com/plugin/18836-golden-radio">
  <img src="https://img.shields.io/jetbrains/plugin/v/18836.svg" alt="Version">
  <img src="https://img.shields.io/jetbrains/plugin/d/18836.svg" alt="Downloads">
  <img src="https://img.shields.io/jetbrains/plugin/r/rating/18836.svg" alt="Rating">
</a>

<!-- Plugin Card -->
<div align="center">
  <a href="https://plugins.jetbrains.com/plugin/18836-golden-radio">
    <img src="https://img.shields.io/badge/Install-JetBrains%20Marketplace-blue?style=for-the-badge&logo=jetbrains" alt="Install from JetBrains Marketplace" height="60">
  </a>
</div>

A powerful IntelliJ IDEA plugin that automatically applies the golden ratio (φ ≈ 1.618) to editor window splits, creating aesthetically pleasing and visually balanced layouts.

## ✨ Features

- **Automatic Golden Ratio Application**: Automatically adjusts editor splitters to follow the golden ratio when switching between files
- **Manual Control**: Trigger golden ratio adjustment manually with a keyboard shortcut (`Ctrl+Shift+G`)
- **Customizable Proportions**: Choose from multiple proportion values (0.5, 0.618, 0.7, 0.8, 0.9) or disable the feature
- **Smart Toggle**: Option to switch between golden ratio and default maximized layouts
- **Auto-Toggle**: Automatically apply golden ratio when opening new files (can be disabled)
- **Seamless Integration**: Works with all IntelliJ-based IDEs

## 🚀 Installation

### From JetBrains Marketplace
1. Open IntelliJ IDEA
2. Go to `File` → `Settings` → `Plugins`
3. Search for "Golden Radio"
4. Click `Install` and restart the IDE

### Manual Installation
1. Download the latest release from the [releases page](https://github.com/QQViaja/golden-radio-intellij-plugin/releases)
2. Go to `File` → `Settings` → `Plugins`
3. Click the gear icon and select `Install Plugin from Disk...`
4. Select the downloaded `.zip` file
5. Restart IntelliJ IDEA

## 🎯 Usage

### Manual Activation
- Use the keyboard shortcut `Ctrl+Shift+G` (or `Cmd+Shift+G` on macOS)
- Or access it through the editor tabs context menu

### Automatic Mode
The plugin can automatically apply the golden ratio when you switch between files. This can be configured in the settings.

## ⚙️ Configuration

Access the plugin settings through:
`File` → `Settings` → `Tools` → `Golden Radio`

### Available Settings

- **Proportion**: Choose your preferred ratio value
  - 0.5 (1:1 ratio)
  - 0.618 (Golden ratio - φ)
  - 0.7, 0.8, 0.9 (Custom ratios)
  - Disable (Turn off the feature)

- **Switch Tabs Between Golden Radio and Default Max**: Toggle between golden ratio and maximized layouts

- **Auto Toggle**: Automatically apply golden ratio when switching between files

## 🎨 What is the Golden Ratio?

The golden ratio (φ ≈ 1.618) is a mathematical ratio that appears frequently in nature and has been used in art and architecture for centuries. It's considered aesthetically pleasing and creates visually balanced compositions. By applying this ratio to your editor splits, you can create a more harmonious and comfortable coding environment.

## 🔧 Development

### Prerequisites
- JDK 17 or higher
- Gradle 8.0+
- IntelliJ IDEA 2025.1+

### Building the Plugin
```bash
./gradlew buildPlugin
```

### Running in Development Mode
```bash
./gradlew runIde
```

### Testing
```bash
./gradlew test
```

## 📝 Changelog

See [CHANGELOG.md](CHANGELOG.md) for detailed release notes.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Kimi Chen** - [QQ Viaja](https://github.com/QQViaja/golden-radio-intellij-plugin)
- Email: kimichen13@gmail.com

## 🙏 Acknowledgments

- Thanks to the JetBrains team for the excellent IntelliJ Platform
- Inspired by the mathematical beauty of the golden ratio
- Special thanks to all contributors and users who provide feedback

## 📊 Compatibility

- **IntelliJ IDEA**: 2025.1+
- **Other JetBrains IDEs**: Compatible with all IntelliJ Platform-based IDEs
- **Operating Systems**: Windows, macOS, Linux

---

*Made with ❤️ by [QQ Viaja](https://github.com/QQViaja/golden-radio-intellij-plugin)*
