pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Reiyaan/MyMavenSeleniumApp01.git'
            }
        }

        stage('Build') {
            steps {
                // Combine these to save time
                sh 'mvn clean package' 
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Run Application') {
            steps {
                // The use of double quotes handles the mainClass property better
                sh 'xvfb-run mvn exec:java -Dexec.mainClass=com.example.App'
            }
        }
    }

    post {
        success {
            // Always try to kill processes so the next build starts fresh
            echo 'Build sucessful'
        }
        failure {
            echo 'Build failed'
        }
    }
}
