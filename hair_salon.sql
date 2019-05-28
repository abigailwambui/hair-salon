--
-- PostgreSQL database dump
--

-- Dumped from database version 10.8 (Ubuntu 10.8-0ubuntu0.18.10.1)
-- Dumped by pg_dump version 10.8 (Ubuntu 10.8-0ubuntu0.18.10.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: abigail
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    name character varying,
    gender character varying,
    phonenumber integer,
    stylistid integer
);


ALTER TABLE public.clients OWNER TO abigail;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: abigail
--

CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO abigail;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: abigail
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: abigail
--

CREATE TABLE public.stylists (
    id integer NOT NULL,
    name character varying,
    phonenumber integer,
    age integer,
    email character varying,
    workexperience character varying
);


ALTER TABLE public.stylists OWNER TO abigail;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: abigail
--

CREATE SEQUENCE public.stylists_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stylists_id_seq OWNER TO abigail;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: abigail
--

ALTER SEQUENCE public.stylists_id_seq OWNED BY public.stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: abigail
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner: abigail
--

ALTER TABLE ONLY public.stylists ALTER COLUMN id SET DEFAULT nextval('public.stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: abigail
--

COPY public.clients (id, name, gender, phonenumber, stylistid) FROM stdin;
\.


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: abigail
--

COPY public.stylists (id, name, phonenumber, age, email, workexperience) FROM stdin;
3	x	766558899	12	abbiew15@hotmail.com	10years worked for Beyonce
5	Mary	766558899	25	mary@gmail.com	5 years for Lizzie Grant
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: abigail
--

SELECT pg_catalog.setval('public.clients_id_seq', 9, true);


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: abigail
--

SELECT pg_catalog.setval('public.stylists_id_seq', 5, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: abigail
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: abigail
--

ALTER TABLE ONLY public.stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

