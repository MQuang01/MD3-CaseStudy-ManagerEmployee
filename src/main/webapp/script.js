const formCaptured = document.querySelector("#formCaptured");



async function loadTrainingData() {
<<<<<<< HEAD
  const labels = ["Minh Quang", "Quoc Anh"];

  const faceDescriptors = [];
  for (const label of labels) {
=======

    const faceDescriptors = [];
>>>>>>> 3a2a4eab524b4193fa1e3be5bcc7167885d615b1
    const descriptors = [];
    for (let i = 1; i <= 4; i++) {
        const image = await faceapi.fetchImage(`/data/${label}/${i}.jpeg`);
        const detection = await faceapi
            .detectSingleFace(image)
            .withFaceLandmarks()
            .withFaceDescriptor();
        descriptors.push(detection.descriptor);


        faceDescriptors.push(
            new faceapi.LabeledFaceDescriptors(label, descriptors)
        );

        Toastify({
            text: `Training xong dữ liệu ${label}`,
        }).showToast();
    }
    return faceDescriptors;
}

let faceMatcher;

async function innit() {
    await Promise.all([
        faceapi.nets.ssdMobilenetv1.loadFromUri("/models"),
        faceapi.nets.faceLandmark68Net.loadFromUri("/models"),
        faceapi.nets.faceRecognitionNet.loadFromUri("/models"),
    ]);

    const trainingData = await loadTrainingData();
    faceMatcher = new faceapi.FaceMatcher(trainingData, 0.5);

    Toastify({
        text: "Tải xong model nhận diện !",
    }).showToast();
    document.getElementById('loadingContainer').style.display = 'none';
    document.getElementById('container').style.display = 'flex';
}

innit();


Webcam.set({
    width: 480,
    height: 360,
    image_format: "jpeg",
    jpeg_quality: 90,
});
Webcam.attach("#camera");

async function snapShotCheckin() {
    Webcam.snap(async function (data_uri) {
        formCaptured.innerHTML =
            '<img id="image" src="' + data_uri + '" height = 450 width = 450 />';

        const capturedImage = document.getElementById("image");

        // Tạo một blob từ ảnh để sử dụng với faceapi.bufferToImage
        capturedImage.crossOrigin = "Anonymous"; // Đảm bảo truy cập tới ảnh qua cors
        const blob = await fetch(data_uri).then((response) => response.blob());

        const image = await faceapi.bufferToImage(blob);
        const canvas = faceapi.createCanvasFromMedia(image);
        formCaptured.append(canvas);

        const size = {
            width: 480,
            height: 360,
        };

        faceapi.matchDimensions(canvas, size);

        const detections = await faceapi
            .detectAllFaces(image)
            .withFaceLandmarks()
            .withFaceDescriptors();
        const resizedDetections = faceapi.resizeResults(detections, size);


        for (const detection of resizedDetections) {
            const bestMatch = faceMatcher.findBestMatch(detection.descriptor);

            if (bestMatch.label == 'unknown') {
                Toastify({
                    text: "Không thể nhận diện. Vui lòng thử lại !",
                }).showToast();
                return;
            }

            const label = `${bestMatch.label} (${Math.round(
                bestMatch.distance * 100
            )}%)`;


            const drawBox = new faceapi.draw.DrawBox(detection.detection.box, {
                label: label,
            });

            drawBox.draw(canvas);
            setTimeout(function (){
                window.location.href = "/timekeeping?act=req-checkin"
            }, 2000)

        }
    });
}

async function snapShotCheckout() {
    Webcam.snap(async function (data_uri) {
        formCaptured.innerHTML =
            '<img id="image" src="' + data_uri + '" height = 450 width = 450 />';

        const capturedImage = document.getElementById("image");

        // Tạo một blob từ ảnh để sử dụng với faceapi.bufferToImage
        capturedImage.crossOrigin = "Anonymous"; // Đảm bảo truy cập tới ảnh qua cors
        const blob = await fetch(data_uri).then((response) => response.blob());

        const image = await faceapi.bufferToImage(blob);
        const canvas = faceapi.createCanvasFromMedia(image);
        formCaptured.append(canvas);

        const size = {
            width: 480,
            height: 360,
        };

        faceapi.matchDimensions(canvas, size);

        const detections = await faceapi
            .detectAllFaces(image)
            .withFaceLandmarks()
            .withFaceDescriptors();
        const resizedDetections = faceapi.resizeResults(detections, size);


        for (const detection of resizedDetections) {
            const bestMatch = faceMatcher.findBestMatch(detection.descriptor);

            if (bestMatch.label == 'unknown') {
                Toastify({
                    text: "Không thể nhận diện. Vui lòng thử lại !",
                }).showToast();
                return;
            }

            const label = `${bestMatch.label} (${Math.round(
                bestMatch.distance * 100
            )}%)`;


            const drawBox = new faceapi.draw.DrawBox(detection.detection.box, {
                label: label,
            });

            drawBox.draw(canvas);
            setTimeout(function (){
                window.location.href = "/timekeeping?act=req-checkout"
            }, 2000)

        }
    });
}


