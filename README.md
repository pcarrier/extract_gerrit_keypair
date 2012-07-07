extract_gerrit_keypair
======================

Extracts the public and private host keys from a Gerrit setup.

The public key can in turn be converted to the classical SSH format with `ssh-keygen -im PKCS8`.

Instructions
------------

    # wget https://github.com/downloads/pcarrier/extract_gerrit_keypair/extract_gerrit_keypair_v0.1.jar
    # java -jar extract_gerrit_keypair_v0.1.jar /path/to/gerrit/etc/ssh_host_key
    -----BEGIN RSA PRIVATE KEY-----
    [...]
    -----END RSA PRIVATE KEY-----

    -----BEGIN PUBLIC KEY-----
    [...]
    -----END PUBLIC KEY-----
