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

// Set up lights
const ambientLight = new THREE.AmbientLight(0xffffff, 0.6);
scene.add(ambientLight);

const dirLight = new THREE.DirectionalLight(0xffffff, 0.8);
dirLight.position.set(200, 500, 300);
scene.add(dirLight);

// Set up camera
const aspectRatio = window.innerWidth / window.innerHeight;
const cameraWidth = 2500;
const cameraHeight = cameraWidth / aspectRatio;

const camera = new THREE.OrthographicCamera(
  cameraWidth / -2, // left
  cameraWidth / 2, // right
  cameraHeight / 2, // top
  cameraHeight / -2, // bottom
  0, // near plane
  1000 // far plane
);
camera.position.set(10, 100, 250);
camera.lookAt(0, 10, 0);

// Set up renderer
const renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.render(scene, camera);

renderer.setAnimationLoop(() => {
  car.rotation.y -= 0.007;
  renderer.render(scene, camera);
});

document.body.appendChild(renderer.domElement);
// ++++++++++++++++++++++++++++++++++++++              CAR FUNTIONS MODELS     +++++++++++++++++++++++++++++++++++++++++ 

function createCar() {
  const car = new THREE.Group();

  const backWheel = createWheels();
  backWheel.position.y = 6;
  backWheel.position.x = -18;
  car.add(backWheel);

  const frontWheel = createWheels();
  frontWheel.position.y = 6;
  frontWheel.position.x = 18;
  car.add(frontWheel);

  const main = new THREE.Mesh(
    new THREE.BoxBufferGeometry(60, 15, 30),
    new THREE.MeshLambertMaterial({ color: 0xa52523 })
  );
  main.position.y = 12;
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
  cabin.position.x = -6;
  cabin.position.y = 25.5;
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
  dimesionSM.position.x = 0;
  StreetModel.add(dimesionSM);

  return StreetModel;
}

function createStreetModel2(){
  const StreetModel2 = new THREE.Group();

  const dimesionSM2 = createDimensionStreet2();
  dimesionSM2.position.y = 0;
  dimesionSM2.position.x = 0;
  StreetModel2.add(dimesionSM2);

  return StreetModel2;
}
// --------------------------------------------------              STREETS MEASURE MODELS          --------------------------------------------------

function createDimensionStreet() {
  const geometry = new THREE.BoxBufferGeometry(1000, 3, 90);
  const material = new THREE.MeshLambertMaterial({ color: 0x8a8a8a });
  const dimension = new THREE.Mesh(geometry, material);
  return dimension;
}

function createDimensionStreet2() {
  const geometry = new THREE.BoxBufferGeometry(90, 3, 1000);
  const material = new THREE.MeshLambertMaterial({ color: 0x6cccff });
  const dimension2 = new THREE.Mesh(geometry, material);
  return dimension2;
}
