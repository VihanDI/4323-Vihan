pipeline {
    agent any 
    
    stages { 
        stage('Get Code from GitHub') {
            steps {
                retry(3) {
                    git branch: 'main', url: 'https://github.com/VihanDI/4323-Vihan'
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {  
                bat 'docker build -t dockerized-app .'
            }
        }
        
        stage('Run Docker Container'){
            steps{
                bat 'docker run -d -p 3000:3000 dockerized-app'
            }
        }
        
        stage('Verify Deployment') {
            steps {
                bat 'docker ps'
            }
        }
    }
}
