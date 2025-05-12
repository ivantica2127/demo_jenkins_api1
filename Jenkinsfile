pipeline {
    agent any

    tools {
        maven 'Maven 3.6'  // El nombre de la instalación de Maven que configuraste en Jenkins
    }

    environment {
        REPO_URL = 'https://github.com/ivantica2127/demo_jenkins_api1.git'
        JAR_NAME = 'app.jar'
        DEPLOY_DIR = '/home/ubuntu/app'
    }

    stages {
        stage('Clonar repositorio') {
            steps {
                git url: "${REPO_URL}", credentialsId: 'github-token'
            }
        }

        stage('Compilar y ejecutar pruebas') {
            steps {
                sh 'mvn clean install'  // Ejecuta pruebas unitarias
            }
        }

        stage('Detener aplicación anterior') {
            steps {
                sh '''
                PID=$(pgrep -f $JAR_NAME || true)
                if [ ! -z "$PID" ]; then
                    echo "Matando proceso $PID"
                    kill $PID
                    sleep 3
                fi
                '''
            }
        }

        stage('Copiar y ejecutar nuevo .jar') {
            steps {
                sh '''
                mkdir -p $DEPLOY_DIR
                cp target/*.jar $DEPLOY_DIR/$JAR_NAME
                nohup java -jar $DEPLOY_DIR/$JAR_NAME > $DEPLOY_DIR/app.log 2>&1 &
                '''
            }
        }
    }

    post {
        failure {
            echo 'Build fallido. Revisa errores de compilacion o pruebas.'
        }
        success {
            echo 'Build y despliegue exitosos.'
        }
    }
}