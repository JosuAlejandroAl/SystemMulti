//      SCENE SHOWED
const scene = new THREE.Scene();



// =================================================              CERATION OF THE FUNTIONS MODELS          ================================================= 
// CAR FUNTIONS
const car = createCar();
scene.add(car);

// STREET FUNTIONS
const StreetModel = createStreetModel();
scene.add(StreetModel);

const StreetModel2 = createStreetModel2();
scene.add(StreetModel2);

// GRASS FUNTIONS

const CampModel2 = createCampModel2();
scene.add(CampModel2);

const CampModel = createCampModel();
scene.add(CampModel);


// Set up lights
const ambientLight = new THREE.AmbientLight(0xffffff, 0.6);
scene.add(ambientLight);

const dirLight = new THREE.DirectionalLight(0xffffff, 0.8);
dirLight.position.set(200, 500, 300);
scene.add(dirLight);

// Set up camera
const aspectRatio = window.innerWidth / window.innerHeight;
const cameraWidth = 2000;
const cameraHeight = cameraWidth / aspectRatio;

const camera = new THREE.OrthographicCamera(
  cameraWidth / -2, // left
  cameraWidth / 2, // right
  cameraHeight / 2, // top
  cameraHeight / -2, // bottom
  0, // near plane
  10000 // far plane
);

camera.position.set(-250, 500, 0); //(-250, 500, 0);
camera.lookAt(0, 0, 0);



// camera.position.set(-100, -90, -50);
// camera.lookAt(-5000, -6000, -5000);

// Set up renderer
const renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.render(scene, camera);

// renderer.setAnimationLoop(() => {
//   car.rotation.y -= 0.007;
  
//   renderer.render(scene, camera);
// });

document.body.appendChild(renderer.domElement);
// ++++++++++++++++++++++++++++++++++++++              CAR FUNTIONS MODELS     +++++++++++++++++++++++++++++++++++++++++ 

function createCar() {
  const car = new THREE.Group();

  const backWheel = createWheels();
  backWheel.position.y = 6;
  backWheel.position.x = -18;
  backWheel.position.z = 250;
  car.add(backWheel);

  const frontWheel = createWheels();
  frontWheel.position.y = 6;
  frontWheel.position.x = 18;
  frontWheel.position.z = 250;
  car.add(frontWheel);

  const main = new THREE.Mesh(
    new THREE.BoxBufferGeometry(60, 15, 30),
    new THREE.MeshLambertMaterial({ color: 0xa52523 })
  );
  main.position.y = 12;
  main.position.x = 0;
  main.position.z = 250;
  car.add(main);

  const carFrontTexture = getCarFrontTexture();

  const carBackTexture = getCarFrontTexture();

  const carRightSideTexture = getCarSideTexture();

  const carLeftSideTexture = getCarSideTexture();
  carLeftSideTexture.center = new THREE.Vector2(0.5, 0.5);
  carLeftSideTexture.rotation = Math.PI;
  carLeftSideTexture.flipY = false;

  const cabin = new THREE.Mesh(new THREE.BoxBufferGeometry(33, 12, 24), [
    new THREE.MeshLambertMaterial({ map: carFrontTexture }),
    new THREE.MeshLambertMaterial({ map: carBackTexture }),
    new THREE.MeshLambertMaterial({ color: 0xffffff }), // top
    new THREE.MeshLambertMaterial({ color: 0xffffff }), // bottom
    new THREE.MeshLambertMaterial({ map: carRightSideTexture }),
    new THREE.MeshLambertMaterial({ map: carLeftSideTexture })
  ]);
  cabin.position.y = 25.5;
  cabin.position.x = -6;
  cabin.position.z = 250
  
  car.add(cabin);

  return car;
}

// ++++++++++++++++++++++++++++++++++++++              CAR MEASURE MODELS          ++++++++++++++++++++++++++++++++++++++ 

function createWheels() {
  const geometry = new THREE.BoxBufferGeometry(12, 12, 33);
  const material = new THREE.MeshLambertMaterial({ color: 0x333333 });
  const wheel = new THREE.Mesh(geometry, material);
  return wheel;
}

function getCarFrontTexture() {
  const canvas = document.createElement("canvas");
  canvas.width = 64;
  canvas.height = 32;
  const context = canvas.getContext("2d");

  context.fillStyle = "#ffffff";
  context.fillRect(0, 0, 64, 32);

  context.fillStyle = "#666666";
  context.fillRect(8, 8, 48, 24);

  return new THREE.CanvasTexture(canvas);
}

function getCarSideTexture() {
  const canvas = document.createElement("canvas");
  canvas.width = 128;
  canvas.height = 32;
  const context = canvas.getContext("2d");

  context.fillStyle = "#ffffff";
  context.fillRect(0, 0, 128, 32);

  context.fillStyle = "#666666";
  context.fillRect(10, 8, 38, 24);
  context.fillRect(58, 8, 60, 24);

  return new THREE.CanvasTexture(canvas);
}

// --------------------------------------------------              STREETS FUNTION MODELS          --------------------------------------------------


function createStreetModel(){
  const StreetModel = new THREE.Group();

  const dimesionSM = createDimensionStreet();
  dimesionSM.position.y = 0;
  dimesionSM.position.x = 240;
  dimesionSM.position.z = 250;
  StreetModel.add(dimesionSM);

  return StreetModel;
}

function createStreetModel2(){
  const StreetModel2 = new THREE.Group();

  const dimesionSM2 = createDimensionStreet2();
  dimesionSM2.position.y = 0;
  dimesionSM2.position.x = 250;
  dimesionSM2.position.z = 250;
  StreetModel2.add(dimesionSM2);

  return StreetModel2;
}
// --------------------------------------------------              STREETS MEASURE MODELS          --------------------------------------------------

// function createDimensionStreet() {
//   const geometry = new THREE.BoxBufferGeometry(10, 3, 90);
//   const material = new THREE.MeshLambertMaterial({ color: 0x8a8a8a });
//   const dimension = new THREE.Mesh(geometry, material);
//   return dimension;
// }

// function createDimensionStreet2() {
//   const geometry = new THREE.BoxBufferGeometry(90, 3, 1000);
//   const material = new THREE.MeshLambertMaterial({ color: 0x6cccff });
//   const dimension2 = new THREE.Mesh(geometry, material);
//   return dimension2;
// }


// --------------------------------------------------              Camp FUNTION MODELS          --------------------------------------------------


function createCampModel(){
  const CampModel = new THREE.Group();

  const dimesionCamp2 = createDimensionCamp();
  dimesionCamp2.position.y = -10;
  dimesionCamp2.position.x = 370;
  dimesionCamp2.position.z = 250;
  CampModel.add(dimesionCamp2);

  const dimesionPost2 = createPost();
  dimesionPost2.position.y = 10;
  dimesionPost2.position.x = 80;
  dimesionPost2.position.z = 70;
  CampModel.add(dimesionPost2);

  const baselight2 = createbaselight2();
  baselight2.position.y = 45;
  baselight2.position.x = 70;
  baselight2.position.z = 70;
  CampModel.add(baselight2);

  const greenlight2 = createlight();
  greenlight2.position.y = 45;
  greenlight2.position.x = 70;
  greenlight2.position.z = 65;
  CampModel.add(greenlight2);

  const yellowlight2 = createlight2();
  yellowlight2.position.y = 45;
  yellowlight2.position.x = 60;
  yellowlight2.position.z = 65;
  CampModel.add(yellowlight2);

  const redlight2 = createlight3();
  redlight2.position.y = 45;
  redlight2.position.x = 80;
  redlight2.position.z = 65;
  CampModel.add(redlight2);

  return CampModel;
}

function createCampModel2(){
  const CampModel2 = new THREE.Group();

  const dimesionCamp2 = createDimensionCamp();
  dimesionCamp2.position.y = -10;
  dimesionCamp2.position.x = 120; //derecha e izquierda
  dimesionCamp2.position.z = 250; //arriba y abajo
  CampModel2.add(dimesionCamp2);

  const dimesionPost = createPost();
  dimesionPost.position.y = 10;
  dimesionPost.position.x = 80;
  dimesionPost.position.z = -70;
  CampModel2.add(dimesionPost);

  const baselight = createbaselight();
  baselight.position.y = 45;
  baselight.position.x = 80;
  baselight.position.z = -55;
  CampModel2.add(baselight);

  const greenlight = createlight();
  greenlight.position.y = 45;
  greenlight.position.x = 75;
  greenlight.position.z = -65;
  CampModel2.add(greenlight);

  const yellowlight = createlight2();
  yellowlight.position.y = 45;
  yellowlight.position.x = 75;
  yellowlight.position.z = -45;
  CampModel2.add(yellowlight);

  const redlight = createlight3();
  redlight.position.y = 45;
  redlight.position.x = 75;
  redlight.position.z = -55;
  CampModel2.add(redlight);

  return CampModel2;
}
// --------------------------------------------------              Camp MEASURE MODELS          --------------------------------------------------

function createDimensionStreet() {
  const geometry = new THREE.BoxBufferGeometry(500, 3, 60);
  const material = new THREE.MeshLambertMaterial({ color: 0x8a8a8a });
  const dimension = new THREE.Mesh(geometry, material);
  return dimension;
}

function createDimensionStreet2() {
  const geometry = new THREE.BoxBufferGeometry(60, 3, 500);
  const material = new THREE.MeshLambertMaterial({ color: 0x8a8a8a });
  const dimension2 = new THREE.Mesh(geometry, material);
  return dimension2;
}

function createDimensionCamp() {
  const geometry = new THREE.BoxBufferGeometry(250, 3, 500);
  const material = new THREE.MeshLambertMaterial({ color: 0x8affae });
  const dimension2 = new THREE.Mesh(geometry, material);
  return dimension2;
}

function createPost() {
  const geometry = new THREE.BoxBufferGeometry(8, 80, 8);
  const material = new THREE.MeshLambertMaterial({ color: 0x595959 });
  const dimension2 = new THREE.Mesh(geometry, material);
  return dimension2;
}

function createbaselight() {
  const geometry = new THREE.BoxBufferGeometry(8, 8, 30);
  const material = new THREE.MeshLambertMaterial({ color: 0x595959 });
  const dimension2 = new THREE.Mesh(geometry, material);
  return dimension2;
}

function createlight() {
  const geometry = new THREE.BoxBufferGeometry(4, 4, 4);
  const material = new THREE.MeshLambertMaterial({ color: 0x61ff00 });
  const dimension2 = new THREE.Mesh(geometry, material);
  return dimension2;
}

function createlight2() {
  const geometry = new THREE.BoxBufferGeometry(4, 4, 4);
  const material = new THREE.MeshLambertMaterial({ color: 0xfffb00 });
  const dimension2 = new THREE.Mesh(geometry, material);
  return dimension2;
}

function createlight3() {
  const geometry = new THREE.BoxBufferGeometry(4, 4, 4);
  const material = new THREE.MeshLambertMaterial({ color: 0xff0000 });
  const dimension2 = new THREE.Mesh(geometry, material);
  return dimension2;
}

function createbaselight2() {

  var geometry2 = new THREE.CylinderGeometry(10,40,100,90,2,false);
  var material2 = new THREE.MeshBasicMaterial({color: 0x00b040, wireframe: true});
  var cone = new THREE.Mesh(geometry2, material2);
  cone.position.set(500, 40, -80);
  scene.add(cone);

  var geometry3 = new THREE.CylinderGeometry(10,40,100,90,2,false);
  var material3 = new THREE.MeshBasicMaterial({color: 0x00b040, wireframe: true});
  var cone = new THREE.Mesh(geometry3, material3);
  cone.position.set(150, 40, -250);
  scene.add(cone);

  var geometry4 = new THREE.CylinderGeometry(10,40,100,90,20,false);
  var material4 = new THREE.MeshBasicMaterial({color: 0x00b040, wireframe: true});
  var cone = new THREE.Mesh(geometry4, material4);
  cone.position.set(-150, 40, 250);
  scene.add(cone);

  var geometry5 = new THREE.CylinderGeometry(10,40,100,90,2,false);
  var material5 = new THREE.MeshBasicMaterial({color: 0x00b040, wireframe: true});
  var cone = new THREE.Mesh(geometry5, material5);
  cone.position.set(-250, 40, 350);
  scene.add(cone);

  var geometry6 = new THREE.CylinderGeometry(10,40,100,90,2,false);
  var material6 = new THREE.MeshBasicMaterial({color: 0x00b040, wireframe: true});
  var cone = new THREE.Mesh(geometry6, material6);
  cone.position.set(-150, 40, -450);
  scene.add(cone);

  var geometry7 = new THREE.CylinderGeometry(10,40,100,90,2,false);
  var material7 = new THREE.MeshBasicMaterial({color: 0x00b040, wireframe: true});
  var cone = new THREE.Mesh(geometry7, material7);
  cone.position.set(350, 40, 350);
  scene.add(cone);

  var geometry8 = new THREE.SphereGeometry(20, 10, 40);
  var material8 = new THREE.MeshBasicMaterial({color: 0xd0a851});
  var pyramid = new THREE.Mesh(geometry8, material8);
  pyramid.position.set(0, 0, 0);
  scene.add(pyramid);

  // var geometry9 = new THREE.TorusGeometry(-200, 100, 100, 100);
  // var material9 = new THREE.MeshBasicMaterial({color: 0x835a02});
  // var torus = new THREE.Mesh(geometry9, material9);
  // torus.position.set(200, 40, 200);
  // scene.add(torus);


  const geometry = new THREE.BoxBufferGeometry(30, 8, 8);
  const material = new THREE.MeshLambertMaterial({ color: 0x595959 });
  const dimension2 = new THREE.Mesh(geometry, material);
  return dimension2;
}

// --------------------------------------------------              Extra ELEMENTS FUNTION MODELS          --------------------------------------------------