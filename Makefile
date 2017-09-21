#
# Copyright (C) 2011 Red Hat, Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#         http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

SHELL := /bin/bash
tag := $(shell cat .openshift-version)

build:
	CGO_ENABLED=0 GO15VENDOREXPERIMENT=1 go build -a ./cmd/generate/generate.go
	./generate > kubernetes-model/src/main/resources/schema/kube-schema.json
	mvn clean source:jar install

gobuild:
	CGO_ENABLED=0 GO15VENDOREXPERIMENT=1 go build -a ./cmd/generate/generate.go
	./generate > kubernetes-model/src/main/resources/schema/kube-schema.json

vendoring:
	glide update --strip-vendor --strip-vcs --update-vendored
	rm -rf vendor/github.com/openshift/origin/vendor vendor/k8s.io/apiextensions-server/vendor vendor/k8s.io/kubernetes/vendor vendor/k8s.io/kubernetes/staging/src/k8s.io/client-go/_vendor

fixvendoring: vendoring
	git reset --hard
	rm -fR vendor/k8s.io/apiextensions-apiserver/examples/client-go/apis/cr/v1/doc.go vendor/k8s.io/apiextensions-apiserver/examples/client-go/apis/cr/v1/roundtrip_test.go vendor/k8s.io/apiextensions-apiserver/examples/client-go/apis/cr/v1/zz_generated.deepcopy.go vendor/k8s.io/apiextensions-apiserver/pkg/apis/apiextensions/fuzzer/ vendor/k8s.io/apiextensions-apiserver/pkg/apis/apiextensions/install/roundtrip_test.go
