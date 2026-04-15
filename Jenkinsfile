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
                sh 'java -jar target/MyMavenSeleniumApp01-1.0-SNAPSHOT.jar'
            }
        }
    }

    post {
        sucess {
            // Always try to kill processes so the next build starts fresh
            sh 'Build sucessful'
        }
        failure {
            echo 'Build failed'
        }
    }
}
