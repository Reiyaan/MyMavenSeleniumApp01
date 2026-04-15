pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Cleanup') {
            steps {
                // Kill any hanging browser processes from previous failed runs
                sh 'pkill -f chromium || true'
                sh 'pkill -f chromedriver || true'
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Reiyaan/MyMavenSeleniumApp01.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Combine these to save time
                sh 'mvn clean package -DskipTests' 
            }
        }

        stage('Run Selenium') {
            steps {
                // The use of double quotes handles the mainClass property better
                sh "mvn exec:java -Dexec.mainClass='com.example.App' -Dexec.cleanupDaemonThreads=false"
            }
        }
    }

    post {
        always {
            // Always try to kill processes so the next build starts fresh
            sh 'pkill -f chromium || true'
        }
    }
}
