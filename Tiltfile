# Build
custom_build(ref='dispatcher-service', command='./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=$EXPECTED_REF', deps=['pom.xml', 'src'])

# Deploy
k8s_yaml([kustomize('k8s'])

# Manage
k8s_resource('dispatcher-service', port_forwards=['9001'])
