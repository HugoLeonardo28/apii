CREATE TABLE oauth_client_details (
	client_id varchar(255) NULL,
	resource_ids varchar(255) NULL,
	client_secret varchar(255) NULL,
	scope varchar(255) NULL,
	authorized_grant_types varchar(255) NULL,
	web_server_redirect_uri varchar(255) NULL,
	authorities varchar(255) NULL,
	access_token_validity integer NULL,
	refresh_token_validity integer NULL,
	additional_information varchar(4096) NULL,
	autoapprove varchar(255) NULL,
	CONSTRAINT oauth_client_details_pk PRIMARY KEY (client_id)
);
