FROM mcr.microsoft.com/devcontainers/base:ubuntu-22.04

# Install Java 21
RUN apt-get update && apt-get install -y openjdk-21-jdk curl git zip unzip tar

# Install Node.js 20
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs

# Install Gradle
RUN curl -s "https://get.sdkman.io" | bash \
    && bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdkman install gradle"

USER vscode
