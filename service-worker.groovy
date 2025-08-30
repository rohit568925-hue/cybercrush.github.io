const CACHE_NAME = 'cyber-crush-cache-v1';
const urlsToCache = [
  '/',
  '/index.html',
  '/courses.html',
  '/student-login.html',
  '/instructor-login.html',
  '/css/style.css', // अगर आपके पास कोई CSS फाइल है तो
  '/js/script.js' // अगर आपके पास कोई JS फाइल है तो
];

// Install event: Cache all the necessary files
self.addEventListener('install', event => {
  event.waitUntil(
    caches.open(CACHE_NAME)
      .then(cache => {
        console.log('Opened cache');
        return cache.addAll(urlsToCache);
      })
  );
});

// Fetch event: Serve cached content when offline
self.addEventListener('fetch', event => {
  event.respondWith(
    caches.match(event.request)
      .then(response => {
        // Return cached response if found
        if (response) {
          return response;
        }
        // If not found, fetch from network
        return fetch(event.request);
      })
  );
});