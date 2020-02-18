ui = true

storage "mysql" {
  address = "mysql:3306"
  username = "vault"
  password = "vault"
  database = "vault"
}

listener "tcp" {
  address     = "0.0.0.0:8200"
  tls_disable = 1
}

telemetry {
  statsite_address = "10.0.0.0:8125"
  disable_hostname = true
}

disable_mlock = true