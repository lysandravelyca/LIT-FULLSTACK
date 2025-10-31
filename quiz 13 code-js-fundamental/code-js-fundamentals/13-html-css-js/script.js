/**
 * OpenAI Landing Page Clone - Animation Script
 *
 * TODO: Add JavaScript for animations
 *
 * Requirements:
 * 1. Sidebar menu toggle functionality
 * 2. Button hover animations
 * 3. Smooth transitions
 *
 * Hints:
 * - Add click event listener to sidebar toggle button
 * - Add mouseenter/mouseleave events to buttons
 * - Use CSS classes to control animations
 */

document.addEventListener("DOMContentLoaded", function () {
  // Sidebar toggle
  const sidebar = document.querySelector(".sidebar");
  const toggleBtn = document.querySelector(".menu-toggle");

  if (sidebar && toggleBtn) {
    toggleBtn.addEventListener("click", () => {
      sidebar.classList.toggle("active"); // Toggle visibility
    });
  }

  // Button hover animations
  const buttons = document.querySelectorAll("button, .btn");

  buttons.forEach((btn) => {
    btn.addEventListener("mouseenter", () => {
      btn.classList.add("hover-animate");
    });

    btn.addEventListener("mouseleave", () => {
      btn.classList.remove("hover-animate");
    });
  });

  // Smooth scroll for internal links
  const links = document.querySelectorAll('a[href^="#"]');

  links.forEach((link) => {
    link.addEventListener("click", (e) => {
      e.preventDefault();
      const target = document.querySelector(link.getAttribute("href"));
      if (target) {
        target.scrollIntoView({ behavior: "smooth", block: "start" });
      }
    });
  });
});
