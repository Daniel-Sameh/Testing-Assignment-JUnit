pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.9'  // Match the name from Maven installations
        jdk 'JDK-22'         // Match the name from JDK installations
    }
    
    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning repository from GitHub...'
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[url: 'https://github.com/Daniel-Sameh/Testing-Assignment-JUnit']]
                ])
            }
        }
        
        stage('Build Part 1 - Java Math Library') {
            steps {
                echo 'Building Part 1: Java Math Library...'
                dir('Part 1/java-math-library-master') {
                    bat 'mvn clean compile'
                }
            }
        }
        
        stage('Test Part 1 - Java Math Library') {
            steps {
                echo 'Running tests for Part 1...'
                dir('Part 1/java-math-library-master') {
                    bat 'mvn test'
                }
            }
            post {
                always {
                    junit 'Part 1/java-math-library-master/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Build Part 2 - Scrubbing') {
            steps {
                echo 'Building Part 2: Scrubbing...'
                dir('Part 2/Scrubbing') {
                    bat 'mvn clean compile'
                }
            }
        }
        
        stage('Test Part 2 - Scrubbing') {
            steps {
                echo 'Running tests for Part 2...'
                dir('Part 2/Scrubbing') {
                    bat 'mvn test'
                }
            }
            post {
                always {
                    junit 'Part 2/Scrubbing/target/surefire-reports/*.xml'
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline completed successfully! All stages executed.'
        }
        unstable {
            echo 'Pipeline completed but some tests failed. Check test reports.'
        }
        failure {
            echo 'Pipeline failed! Check logs for errors.'
        }
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
