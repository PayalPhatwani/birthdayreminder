import React, { useEffect, useState } from "react";
import "./App.css";
import {
  Button,
  Card,
  CardBody,
  CardTitle,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  Label,
  ListGroup,
  ListGroupItem,
  Modal,
  ModalBody,
  ModalHeader,
  Row,
} from "reactstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBirthdayCake,
  faCheck,
  faPaperPlane,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
<link
  href="https://fonts.googleapis.com/css?family=Antic"
  rel="stylesheet"
></link>;

function App() {
  const [modal, setModal] = useState(false);
  const toggle = () => setModal(!modal);
  const [people, setPeople] = useState([]);
  const [personToWish, setperson] = useState({});
  const [email, setemail] = useState({ id: 0, subject: "", emailBody: "" });

  //handle email form
  const handleForm = (e) => {
    e.preventDefault();
    //sending form to the server
    axios
      .post(`http://localhost:8080/people/sendemail/${email.id}`, email)
      .then(
        (response) => {
          console.log(response.data);
          toggle();
          toast(
            <b style={{ color: "black" }}>
              Sent Birthday Wish <br></br>
              <small>to {personToWish.name}</small>
            </b>,
            {
              position: "bottom-center",
            }
          );
          updatePeople(personToWish.id);

          setperson({});
          setemail({ id: 0, subject: "", emailBody: "" });
        },
        (error) => {
          toggle();
          console.log("error occured");
          console.log(error);
          toast(
            <b style={{ color: "black" }}>
              Ooopss <br></br>
              <small>Some error occurred</small>
            </b>,
            {
              position: "bottom-center",
            }
          );
          setperson({});
          setemail({ id: 0, subject: "", emailBody: "" });
        }
      );
  };

  //handle change form
  const handleChange = (e) => {
    const name = e.target.name;
    const value = e.target.value;
    setemail({ ...email, [name]: value, id: personToWish.id });
  };

  //getting data from the server
  const getDataFromServer = () => {
    axios.get(`http://localhost:8080/people/getList`).then(
      (response) => {
        console.log(response.data);
        console.log("success");
        setPeople(response.data);
      },
      (error) => {
        console.log(error);
        console.log("error occured");
      }
    );
  };

  //for loading details
  useEffect(() => {
    getDataFromServer();
  }, []);

  //sending
  const updatePeople = (id) => {
    const updatePeople = people.filter((person) => {
      if (person.id === id) {
        person.wished = true;
        return person;
      } else {
        return person;
      }
    });
    setPeople(updatePeople);
  };

  //return the component which has been displayed on the screen
  return (
    <>
      <ToastContainer />

      <div className="container">
        <Card className="card ">
          <CardTitle className="cardTitle ml-5">
            {" "}
            {people.length} Birthdays Today{" "}
            <FontAwesomeIcon icon={faBirthdayCake} />
          </CardTitle>
          <CardBody>
            <ListGroup flush>
              {people.map((person) => {
                const { id, pictureUrl, name, dob, wished } = person;
                return (
                  <Container className="ml-2 list" key={person.id}>
                    <Row className="border-bottom">
                      <Col className="m-2 p-0 " xs="2">
                        <img
                          src={pictureUrl}
                          width="68"
                          height="68"
                          className="image"
                          alt={name}
                        />
                      </Col>
                      <Col className="m-2 p-0" xs="5">
                        <b>{name}</b> <br></br> {dob}
                      </Col>
                      <Col className="m-4 p-0 ">
                        {!wished ? (
                          <a
                            href="#"
                            type="button"
                            onClick={() => {
                              console.log(typeof id);
                              setperson(person);
                              console.log(personToWish);
                              toggle();
                            }}
                          >
                            <FontAwesomeIcon
                              className="icons"
                              icon={faPaperPlane}
                              title="Wish Now"
                              size="2x"
                              id="TooltipExample"
                            />
                          </a>
                        ) : (
                          <FontAwesomeIcon
                            className="icons"
                            icon={faCheck}
                            title="Wished"
                            size="2x"
                          />
                        )}
                      </Col>
                    </Row>
                  </Container>
                );
              })}

              <ListGroupItem>
                <Button
                  className="button"
                  size="lg"
                  block
                  onClick={() => setPeople([])}
                >
                  Clear All
                </Button>
              </ListGroupItem>
            </ListGroup>
          </CardBody>
        </Card>
        <Modal isOpen={modal}>
          <ModalHeader>
            Sending Birthday Wish to <b>{personToWish.name} !</b> <br />
            (sending via email)
          </ModalHeader>
          <ModalBody>
            <Form onSubmit={handleForm}>
              <FormGroup>
                <Label for="exampleEmail">Subject</Label>
                <Input
                  type="text"
                  name="subject"
                  id="subject"
                  value={email.subject}
                  onChange={handleChange}
                  placeholder="enter email subject"
                  required
                />
              </FormGroup>
              <FormGroup>
                <Label for="examplePassword">Message</Label>
                <Input
                  type="textarea"
                  name="emailBody"
                  id="emailBody"
                  value={email.emailBody}
                  onChange={handleChange}
                  placeholder="enter email body"
                  required
                />
              </FormGroup>
              <hr></hr>
              <Button color="primary" type="submit">
                Send Wish
              </Button>{" "}
              <Button
                color="secondary"
                onClick={() => {
                  setperson({});
                  toggle();
                  setemail({ id: 0, subject: "", emailBody: "" });
                }}
              >
                Cancel
              </Button>
            </Form>
          </ModalBody>
        </Modal>
      </div>
    </>
  );
}

export default App;
