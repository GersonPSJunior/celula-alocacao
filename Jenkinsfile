pipeline {
    agent any
    stages {
        stage('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                bat 'docker-compose -f sonar.yml up -d'
                withSonarQubeEnv('SONAR_LOCAL'){
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployAPI -Dsonar.host.url=http://localhost:9000 -Dsonar.login=11cbfd6d5c03103fcbeb519de6a5f7dba5d014f5 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/igrejaalocacao/config/**,Application.java"
                }
            }
        }
    }
}

