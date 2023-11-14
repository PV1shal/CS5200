function scrollToHosts() {
    // Get the offset of the target section
    var targetOffset = document.getElementById('hosts').offsetTop;

    // Scroll to the target section smoothly
    window.scrollTo({
        top: targetOffset,
        behavior: 'smooth'
    });
}