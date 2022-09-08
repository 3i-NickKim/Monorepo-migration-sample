package vars

def G_POD_TEMPLATE = """
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
