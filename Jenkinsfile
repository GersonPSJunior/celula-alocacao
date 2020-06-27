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
        stage('Run Sonar docker-compose') {
            steps {
                bat 'docker-compose -f sonar.yml up'
            }
        }
        stage('Sonar Analysis') {
            environments {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL'){
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployAPI -Dsonar.host.url=http://localhost:9000 -Dsonar.login=a018537a5e79677ab11400414da936e8df5e4c60 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/igrejaalocacao/config/**,Application.java"
                }
            }
        }
    }
}

