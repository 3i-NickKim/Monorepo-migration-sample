package vars

def GLOBAL_POD_TEMPLATE() {
     return """
     api version: v1
     kind: Pod
     spec:
       nodeSelector:
         service: cicd-builders
       tolerations:
       - key: "builder"
         operator: "Equal"
         value: "true"
         effect: "NoSchedule"
       containers:
       - name: pivo-build-img
         image: 3ijunejoo/pivo-build-img:latest
         command:
         - sleep
         args:
         - infinity
     """
}

def NODE_POD_TEMPLATE_TEST() {
     return '''
     api version: v1
     kind: Pod
     spec:
       nodeSelector:
         service: cicd-builders
       tolerations:
       - key: "builder"
         operator: "Equal"
         value: "true"
         effect: "NoSchedule"
       containers:
       - name: pivo-docker-build-image
         image: 3ijunejoo/pivo-docker-build-image:latest
         imagePullPolicy: Always
         securityContext:
           privileged: true
         volumeMounts:
         - mountPath: /var/run
           name: docker-socket
         - name: cicd-storage
           mountPath: /root/.aws
           readOnly: true
         command:
         - sleep
         args:
         - infinity
       volumes:
       - name: docker-socket
         hostPath:
           path: /var/run
           type: Directory
       - name: cicd-storage
         persistentVolumeClaim:
           claimName: cicd-aws-claim
     '''
}
